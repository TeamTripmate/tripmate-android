package com.tripmate.android.feature.map.overlay

import android.graphics.Bitmap
import android.graphics.PointF
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.Updater
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ComposeView
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelTransition
import com.kakao.vectormap.label.TransformMethod
import com.kakao.vectormap.label.Transition
import com.tripmate.android.feature.map.extension.getMap
import com.tripmate.android.feature.map.extension.startTrackingCompat
import com.tripmate.android.feature.map.extension.stopTrackingCompat
import com.tripmate.android.feature.map.internal.MapApplier
import com.tripmate.android.feature.map.internal.node.LabelNode
import com.tripmate.android.feature.map.internal.renderComposeViewOnce
import com.tripmate.android.feature.map.internal.toNativeBitmap
import com.tripmate.android.feature.map.state.LocalCameraPositionState
import io.hlab.vectormap.compose.annotation.KakaoMapComposable
import java.util.UUID

/**
 * Label 의 설정 값에 대한 Default Value Provider
 */
public object LabelDefaults {
    public const val MinZoom: Int = 0
    public const val MoveDuration: Int = 300

    public val MapLabelTransition: LabelTransition = LabelTransition.from(Transition.Alpha, Transition.Alpha)
    public val Transform: TransformMethod = TransformMethod.Default
    public val Anchor: Offset = Offset(0.5f, 0.5f)
}

/**
 * [Label] Composable
 *
 * @param position 라벨이 표시될 지리적 위치 ([LatLng]) 을 지정합니다.
 * @param iconResId 아이콘으로 사용할 이미지 리소스를 지정합니다. Vector Drawable 은 kakao-map 정책 상 지원하지 않습니다.
 * @param anchor 앵커를 지원한다. 앵커는 아이콘 이미지에서 기준이 되는 지점을 의미합니다. 앵커로 지정된 지점이 마커의 좌표에 위치하게 됩니다.
 * 값의 범위는 (0, 0)~(1, 1) 이며, (0, 0) 일 경우 이미지의 왼쪽 위 / (1, 1) 일 경우 이미지의 오른쪽 아래에 위치합니다.
 * 기본값은 [LabelDefaults.Anchor] 이며 정 중앙에 위치하게 됩니다.
 * @param rotate 아이콘의 각도를 지정합니다. 각도를 지정하면 아이콘이 해당 각도만큼 시계 방향으로 회전합니다. 기본값은 0입니다.
 * @param labelId 라벨의 아이디를 지정합니다. 기본값은 null 이며 이 경우, 유니크한 값으로 자동 생성됩니다.
 * @param tag 태그를 지정합니다. 기본값은 null 입니다.
 * @param rank 랭크를 지정합니다. 기본값은 0 입니다.
 * @param transition 라벨이 나타나고 사라질 때의 애니메이션을 지정합니다. 기본값은 [LabelDefaults.MapLabelTransition] 입니다.
 * @param transform 라벨의 변환을 지정합니다. 지도가 움직일 때 라벨이 어떤 자세를 유지할 지를 의미합니다.
 *  * [TransformMethod] 를 이용해 지정하여 기본값은 [LabelDefaults.Transform] 이며 지도의 변화에 상관 없이 형태를 유지합니다.
 * @param enableAnimateMove [position] 값이 변경됨에 따라 라벨이 이동할 때, 애니메이션을 적용해 부드럽게 이동시킬 지를 지정합니다.
 * 기본값은 [LabelDefaults.MoveDuration] 가 true 일 경우, [animateMoveDuration] 의 값에 따라 애니메이션을 적용합니다.
 * @param animateMoveDuration [animateMoveDuration] 가 허용된 경우 적용될 애니메이션 시간 (millis) 을 지정합니다.
 * 기본값은 [LabelDefaults.MoveDuration] 이며 300 millisecond 입니다.
 * @param isVisible 라벨이 가시성을 지정합니다. 가시성이 false 일 경우 오버레이는 화면에 나타나지 않습니다.
 * 가시성은 명시적으로 지정하지 않는 한 변하지 않습니다. 즉, 현재 카메라 영역이 오버레이 위치를 담지 못 하더라도, 가시성이 false 로 변하지 않습니다.
 * 기본값은 true 입니다.
 * @param isClickable 라벨의 OnClick 이벤트 실행 가능 여부를 지정합니다. 기본값은 true 입니다.
 * @param onClick 해당 라벨이 클릭 되었을 때의 처리를 위임한다. 컴포즈 에서는 기존과 같이 LabelOnClickListener 를 별도로 선언해서 사용하는 것보다,
 * 각 라벨노드에 위임하고 실제 실행은 내부적으로 매핑해서 맵 객체에 제공하는 것이 보다 직관적이라고 판단해 이와 같이 제공합니다.
 * @param isApplyDpScale 아이콘 이밎와 텍스트의 크기에 영향을 줍니다. 이 옵션이 true 라면 지도 객체 내부에서 DP 를 적용해 컨트롤 하게끔 하여
 * 해상도가 달라져도 아이콘 이미지와 텍스트 크기에 영향을 주지 않게 됩니다. 기본값은 true 입니다.
 * @param isTracking 라벨이 이동할 때, 지도 중심이 라벨에 맞춰지게 할 지를 지정합니다. 기본값은 false 입니다.
 * 해당 값이 변경되어 트래킹이 시작/종료되는 시점에, [io.hlab.vectormap.compose.state.CameraPositionState] 을 갱신합니다.
 * @param minZoom 오버레이가 보여질 최소 줌 레벨을 지정하빈다. 기본값은 [LabelDefaults.MinZoom] 입니다.
 * @param children 이 라벨의 변형 ( move / rotate / etc. ) 을 따라 행동할 오버레이 컴포저블을 추가합니다.
 * 현재 vecotrmap API 에서 shareTransform 옵션을 < 대상자 : 라벨 / 요청자 : 라벨 / 폴리곤 > 에 대해서만 제공하므로
 * 이를 충분히 고려할 수 있어야 합니다.
 */
@KakaoMapComposable
@Composable
public fun Label(
    position: LatLng,
    @DrawableRes iconResId: Int? = null,
    anchor: Offset = LabelDefaults.Anchor,
    rotate: Float = 0f,
    labelId: String? = null,
    tag: String? = null,
    rank: Long = 0L,
    transition: LabelTransition = LabelDefaults.MapLabelTransition,
    transform: TransformMethod = LabelDefaults.Transform,
    enableAnimateMove: Boolean = true,
    animateMoveDuration: Int = LabelDefaults.MoveDuration,
    isVisible: Boolean = true,
    isClickable: Boolean = true,
    onClick: (Label) -> Unit = {},
    isApplyDpScale: Boolean = true,
    isTracking: Boolean = false,
    minZoom: Int = LabelDefaults.MinZoom,
    children:
    @KakaoMapComposable @Composable
    () -> Unit = {},
) {
    LabelImpl(
        position = position,
        iconResId = iconResId,
        anchor = anchor,
        rotate = rotate,
        labelId = labelId,
        transition = transition,
        tag = tag,
        rank = rank,
        transform = transform,
        enableAnimateMove = enableAnimateMove,
        animateMoveDuration = animateMoveDuration,
        isVisible = isVisible,
        isClickable = isClickable,
        onClick = onClick,
        isApplyDpScale = isApplyDpScale,
        isTracking = isTracking,
        minZoom = minZoom,
        children = children,
    )
}

/**
 * [Label] Composable
 *
 * @param position 라벨이 표시될 지리적 위치 ([LatLng]) 을 지정합니다.
 * @param bitmap 아이콘으로 사용할 비트맵 객체를 지정합니다.
 * @param anchor 앵커를 지원한다. 앵커는 아이콘 이미지에서 기준이 되는 지점을 의미합니다. 앵커로 지정된 지점이 마커의 좌표에 위치하게 됩니다.
 * 값의 범위는 (0, 0)~(1, 1) 이며, (0, 0) 일 경우 이미지의 왼쪽 위 / (1, 1) 일 경우 이미지의 오른쪽 아래에 위치합니다.
 * 기본값은 [LabelDefaults.Anchor] 이며 정 중앙에 위치하게 됩니다.
 * @param rotate 아이콘의 각도를 지정합니다. 각도를 지정하면 아이콘이 해당 각도만큼 시계 방향으로 회전합니다. 기본값은 0입니다.
 * @param labelId 라벨의 아이디를 지정합니다. 기본값은 null 이며 이 경우, 유니크한 값으로 자동 생성됩니다.
 * @param tag 태그를 지정합니다. 기본값은 null 입니다.
 * @param rank 랭크를 지정합니다. 기본값은 0 입니다.
 * @param transition 라벨이 나타나고 사라질 때의 애니메이션을 지정합니다. 기본값은 [LabelDefaults.MapLabelTransition] 입니다.
 * @param transform 라벨의 변환을 지정합니다. 지도가 움직일 때 라벨이 어떤 자세를 유지할 지를 의미합니다.
 *  * [TransformMethod] 를 이용해 지정하여 기본값은 [LabelDefaults.Transform] 이며 지도의 변화에 상관 없이 형태를 유지합니다.
 * @param enableAnimateMove [position] 값이 변경됨에 따라 라벨이 이동할 때, 애니메이션을 적용해 부드럽게 이동시킬 지를 지정합니다.
 * 기본값은 [LabelDefaults.MoveDuration] 가 true 일 경우, [animateMoveDuration] 의 값에 따라 애니메이션을 적용합니다.
 * @param animateMoveDuration [animateMoveDuration] 가 허용된 경우 적용될 애니메이션 시간 (millis) 을 지정합니다.
 * 기본값은 [LabelDefaults.MoveDuration] 이며 300 millisecond 입니다.
 * @param isVisible 라벨이 가시성을 지정합니다. 가시성이 false 일 경우 오버레이는 화면에 나타나지 않습니다.
 * 가시성은 명시적으로 지정하지 않는 한 변하지 않습니다. 즉, 현재 카메라 영역이 오버레이 위치를 담지 못 하더라도, 가시성이 false 로 변하지 않습니다.
 * 기본값은 true 입니다.
 * @param isClickable 라벨의 OnClick 이벤트 실행 가능 여부를 지정합니다. 기본값은 true 입니다.
 * @param onClick 해당 라벨이 클릭 되었을 때의 처리를 위임한다. 컴포즈 에서는 기존과 같이 LabelOnClickListener 를 별도로 선언해서 사용하는 것보다,
 * 각 라벨노드에 위임하고 실제 실행은 내부적으로 매핑해서 맵 객체에 제공하는 것이 보다 직관적이라고 판단해 이와 같이 제공합니다.
 * @param isApplyDpScale 아이콘 이밎와 텍스트의 크기에 영향을 줍니다. 이 옵션이 true 라면 지도 객체 내부에서 DP 를 적용해 컨트롤 하게끔 하여
 * 해상도가 달라져도 아이콘 이미지와 텍스트 크기에 영향을 주지 않게 됩니다. 기본값은 true 입니다.
 * @param isTracking 라벨이 이동할 때, 지도 중심이 라벨에 맞춰지게 할 지를 지정합니다. 기본값은 false 입니다.
 * 해당 값이 변경되어 트래킹이 시작/종료되는 시점에, [io.hlab.vectormap.compose.state.CameraPositionState] 을 갱신합니다.
 * @param minZoom 오버레이가 보여질 최소 줌 레벨을 지정하빈다. 기본값은 [LabelDefaults.MinZoom] 입니다.
 * @param children 이 라벨의 변형 ( move / rotate / etc. ) 을 따라 행동할 오버레이 컴포저블을 추가합니다.
 * 현재 vecotrmap API 에서 shareTransform 옵션을 < 대상자 : 라벨 / 요청자 : 라벨 / 폴리곤 > 에 대해서만 제공하므로
 * 이를 충분히 고려할 수 있어야 합니다.
 */
@KakaoMapComposable
@Composable
public fun Label(
    position: LatLng,
    bitmap: Bitmap? = null,
    anchor: Offset = LabelDefaults.Anchor,
    rotate: Float = 0f,
    labelId: String? = null,
    tag: String? = null,
    rank: Long = 0L,
    transition: LabelTransition = LabelDefaults.MapLabelTransition,
    transform: TransformMethod = LabelDefaults.Transform,
    enableAnimateMove: Boolean = true,
    animateMoveDuration: Int = LabelDefaults.MoveDuration,
    isVisible: Boolean = true,
    isClickable: Boolean = true,
    onClick: (Label) -> Unit = {},
    isApplyDpScale: Boolean = true,
    isTracking: Boolean = false,
    minZoom: Int = LabelDefaults.MinZoom,
    children:
    @KakaoMapComposable @Composable
    () -> Unit = {},
) {
    LabelImpl(
        position = position,
        bitmap = bitmap,
        anchor = anchor,
        rotate = rotate,
        labelId = labelId,
        transition = transition,
        tag = tag,
        rank = rank,
        transform = transform,
        enableAnimateMove = enableAnimateMove,
        animateMoveDuration = animateMoveDuration,
        isVisible = isVisible,
        isClickable = isClickable,
        onClick = onClick,
        isApplyDpScale = isApplyDpScale,
        isTracking = isTracking,
        minZoom = minZoom,
        children = children,
    )
}

/**
 * [Label] Composable
 *
 * @param position 라벨이 표시될 지리적 위치 ([LatLng]) 을 지정합니다.
 * @param icon 아이콘으로 사용할 컴포즈 뷰를 지정합니다.
 * @param iconId 아이콘으로 사용되는 컴포즈 뷰의 변화를 감지할 UniqueId 를 지정합니다.
 * 컴포즈 뷰에 대한 비트맵 생성은 큰 비용을 요하므로 갱신이 필요할 때, [iconId] 값의 변화를 트리거로 사용할 수 있도록 지원합니다.
 * @param anchor 앵커를 지원한다. 앵커는 아이콘 이미지에서 기준이 되는 지점을 의미합니다. 앵커로 지정된 지점이 마커의 좌표에 위치하게 됩니다.
 * 값의 범위는 (0, 0)~(1, 1) 이며, (0, 0) 일 경우 이미지의 왼쪽 위 / (1, 1) 일 경우 이미지의 오른쪽 아래에 위치합니다.
 * 기본값은 [LabelDefaults.Anchor] 이며 정 중앙에 위치하게 됩니다.
 * @param rotate 아이콘의 각도를 지정합니다. 각도를 지정하면 아이콘이 해당 각도만큼 시계 방향으로 회전합니다. 기본값은 0입니다.
 * @param labelId 라벨의 아이디를 지정합니다. 기본값은 null 이며 이 경우, 유니크한 값으로 자동 생성됩니다.
 * @param tag 태그를 지정합니다. 기본값은 null 입니다.
 * @param rank 랭크를 지정합니다. 기본값은 0 입니다.
 * @param transition 라벨이 나타나고 사라질 때의 애니메이션을 지정합니다. 기본값은 [LabelDefaults.MapLabelTransition] 입니다.
 * @param transform 라벨의 변환을 지정합니다. 지도가 움직일 때 라벨이 어떤 자세를 유지할 지를 의미합니다.
 *  * [TransformMethod] 를 이용해 지정하여 기본값은 [LabelDefaults.Transform] 이며 지도의 변화에 상관 없이 형태를 유지합니다.
 * @param enableAnimateMove [position] 값이 변경됨에 따라 라벨이 이동할 때, 애니메이션을 적용해 부드럽게 이동시킬 지를 지정합니다.
 * 기본값은 [LabelDefaults.MoveDuration] 가 true 일 경우, [animateMoveDuration] 의 값에 따라 애니메이션을 적용합니다.
 * @param animateMoveDuration [animateMoveDuration] 가 허용된 경우 적용될 애니메이션 시간 (millis) 을 지정합니다.
 * 기본값은 [LabelDefaults.MoveDuration] 이며 300 millisecond 입니다.
 * @param isVisible 라벨이 가시성을 지정합니다. 가시성이 false 일 경우 오버레이는 화면에 나타나지 않습니다.
 * 가시성은 명시적으로 지정하지 않는 한 변하지 않습니다. 즉, 현재 카메라 영역이 오버레이 위치를 담지 못 하더라도, 가시성이 false 로 변하지 않습니다.
 * 기본값은 true 입니다.
 * @param isClickable 라벨의 OnClick 이벤트 실행 가능 여부를 지정합니다. 기본값은 true 입니다.
 * @param onClick 해당 라벨이 클릭 되었을 때의 처리를 위임한다. 컴포즈 에서는 기존과 같이 LabelOnClickListener 를 별도로 선언해서 사용하는 것보다,
 * 각 라벨노드에 위임하고 실제 실행은 내부적으로 매핑해서 맵 객체에 제공하는 것이 보다 직관적이라고 판단해 이와 같이 제공합니다.
 * @param isApplyDpScale 아이콘 이밎와 텍스트의 크기에 영향을 줍니다. 이 옵션이 true 라면 지도 객체 내부에서 DP 를 적용해 컨트롤 하게끔 하여
 * 해상도가 달라져도 아이콘 이미지와 텍스트 크기에 영향을 주지 않게 됩니다. 기본값은 true 입니다.
 * @param isTracking 라벨이 이동할 때, 지도 중심이 라벨에 맞춰지게 할 지를 지정합니다. 기본값은 false 입니다.
 * 해당 값이 변경되어 트래킹이 시작/종료되는 시점에, [io.hlab.vectormap.compose.state.CameraPositionState] 을 갱신합니다.
 * @param minZoom 오버레이가 보여질 최소 줌 레벨을 지정하빈다. 기본값은 [LabelDefaults.MinZoom] 입니다.
 * @param children 이 라벨의 변형 ( move / rotate / etc. ) 을 따라 행동할 오버레이 컴포저블을 추가합니다.
 * 현재 vecotrmap API 에서 shareTransform 옵션을 < 대상자 : 라벨 / 요청자 : 라벨 / 폴리곤 > 에 대해서만 제공하므로
 * 이를 충분히 고려할 수 있어야 합니다.
 */
@KakaoMapComposable
@Composable
public fun Label(
    position: LatLng,
    icon: @Composable () -> Unit,
    iconId: String = remember { UUID.randomUUID().toString() },
    anchor: Offset = LabelDefaults.Anchor,
    rotate: Float = 0f,
    labelId: String? = null,
    tag: String? = null,
    rank: Long = 0L,
    transition: LabelTransition = LabelDefaults.MapLabelTransition,
    transform: TransformMethod = LabelDefaults.Transform,
    enableAnimateMove: Boolean = true,
    animateMoveDuration: Int = LabelDefaults.MoveDuration,
    isVisible: Boolean = true,
    isClickable: Boolean = true,
    onClick: (Label) -> Unit = {},
    isApplyDpScale: Boolean = true,
    isTracking: Boolean = false,
    minZoom: Int = LabelDefaults.MinZoom,
    children:
    @KakaoMapComposable @Composable
    () -> Unit = {},
) {
    LabelImpl(
        position = position,
        icon = icon,
        iconId = iconId,
        anchor = anchor,
        rotate = rotate,
        labelId = labelId,
        transition = transition,
        tag = tag,
        rank = rank,
        transform = transform,
        enableAnimateMove = enableAnimateMove,
        animateMoveDuration = animateMoveDuration,
        isVisible = isVisible,
        isClickable = isClickable,
        onClick = onClick,
        isApplyDpScale = isApplyDpScale,
        isTracking = isTracking,
        minZoom = minZoom,
        children = children,
    )
}

/**
 * [Label] 의 [ComposeNode] 구현체
 *
 * - [LabelStyle] 및 [LabelOptions] 에 대한 실제 오버레이 적용을 담당한다.
 * - 실제 MapView 에서 관리되는 Label 에 대한 관리 및 처리를 Compose 로 위임해 처리할 수 있도록 한다.
 */
@KakaoMapComposable
@Composable
private fun LabelImpl(
    position: LatLng,
    @DrawableRes iconResId: Int? = null,
    bitmap: Bitmap? = null,
    icon: (@Composable () -> Unit)? = null,
    iconId: String? = null,
    anchor: Offset = LabelDefaults.Anchor,
    rotate: Float = 0f,
    labelId: String? = null,
    tag: String? = null,
    rank: Long = 0L,
    transition: LabelTransition = LabelDefaults.MapLabelTransition,
    transform: TransformMethod = LabelDefaults.Transform,
    enableAnimateMove: Boolean = true,
    animateMoveDuration: Int = LabelDefaults.MoveDuration,
    isVisible: Boolean = true,
    isClickable: Boolean = true,
    onClick: (Label) -> Unit = {},
    isApplyDpScale: Boolean = true,
    isTracking: Boolean = false,
    minZoom: Int = LabelDefaults.MinZoom,
    children:
    @KakaoMapComposable @Composable
    () -> Unit = {},
) {
    val applier = checkNotNull(currentComposer.applier as? MapApplier)
    val map = getMap()
    val mapView = applier.mapView
    val currentCameraPositionState = LocalCameraPositionState.current
    val compositionContext = rememberCompositionContext()
    // style 을 remember 해 다시 그려져야 하는 경우만 bitmap 등에 대해 새로 그릴 수 있도록 처리함. (퍼포먼스 개선)
    val style: LabelStyle = remember(iconResId, bitmap, iconId, isApplyDpScale, anchor, minZoom, transition) {
        val impl = when {
            iconResId != null -> LabelStyle.from(iconResId)
            bitmap != null -> LabelStyle.from(bitmap)
            icon != null -> {
                val view = ComposeView(mapView.context).apply {
                    setContent(icon)
                }
                mapView.renderComposeViewOnce(view, parentContext = compositionContext)
                LabelStyle.from(view.toNativeBitmap())
            }
            else -> error("Error while adding Label!")
        }
        impl.apply {
            setApplyDpScale(isApplyDpScale)
            anchorPoint = PointF(anchor.x, anchor.y)
            setZoomLevel(minZoom)
            iconTransition = transition
        }
    }

    // node factory function
    fun nodeFactory(): LabelNode {
        val options = if (labelId != null) {
            LabelOptions.from(labelId, position)
        } else {
            LabelOptions.from(position)
        }.apply {
            tag?.let { setTag(it) }
            setStyles(style)
            setTransform(transform)
            setRank(rank)
            setClickable(isClickable)
            setVisible(isVisible)
        }
        // Label 생성 후 property 변경 등을 용이하게 하기 위해 인스턴스를 가져온다.
        val overlay = checkNotNull(map.labelManager?.layer?.addLabel(options)) {
            "Error adding Label!"
        }

        if (isTracking) map.trackingManager?.startTracking(overlay)

        if (rotate != 0f) overlay.rotateTo(rotate)

        return LabelNode(
            overlay = overlay,
            onLabelClick = onClick,
        )
    }

    // node updater function
    fun Updater<LabelNode>.updater() {
        update(onClick) { this.onLabelClick = it }
        // set label styles
        update(style) { style ->
            val prevStyles = this.overlay.styles.getStyles()
            with(this.overlay) {
                setStyles(style)
                invalidate() // 명시적으로 style 변경을 요청을 해 변경이 이뤄지도록 한다.
            }
            prevStyles.forEach { it.iconBitmap?.recycle() }
        }
        // set label position
        update(position) { pos ->
            if (enableAnimateMove) {
                this.overlay.moveTo(pos, animateMoveDuration)
            } else {
                this.overlay.moveTo(pos)
            }
        }
        update(rotate) { this.overlay.rotateTo(it, 500) }
        update(isTracking) { enableTracking ->
            val trackingManager = map.trackingManager ?: return@update
            if (enableTracking) {
                trackingManager.startTrackingCompat(
                    label = this.overlay,
                    cameraPositionState = currentCameraPositionState,
                )
            } else {
                trackingManager.stopTrackingCompat(cameraPositionState = currentCameraPositionState)
            }
        }
        // label options control
        update(tag) { tag -> tag?.let { this.overlay.tag = it } }
        update(rank) { this.overlay.changeRank(it) }
        update(isVisible) { visible -> if (visible) this.overlay.show() else this.overlay.hide() }
        update(isClickable) { this.overlay.isClickable = it }
    }

    ComposeNode<LabelNode, MapApplier>(
        factory = ::nodeFactory,
        update = { updater() },
        content = children,
    )
}
