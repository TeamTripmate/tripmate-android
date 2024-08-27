package com.tripmate.android.feature.map.internal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.platform.AbstractComposeView
import com.kakao.vectormap.MapView
import com.tripmate.android.feature.map.R
import java.io.Closeable

/**
 * [view] 를 [MapView] 에 일시적으로 attach 해 그려지도록 한다.
 *
 * [androidx.compose.ui.platform.ComposeView] 의 composition 이 일어나기 위해서는 window 에 attach 되어야 하기 때문에,
 * view 를 그리기 위해서 [MapView] 에 일시적으로 부착한 뒤 바로 제거하는 hacky 한 방법을 사용한다.
 */
internal fun MapView.renderComposeViewOnce(
    view: AbstractComposeView,
    onAddedToWindow: ((View) -> Unit)? = null,
    parentContext: CompositionContext,
) {
    startRenderingComposeView(view, parentContext).use {
        onAddedToWindow?.invoke(view)
    }
}

/**
 * [view] 를 [MapView] 의 컨텍스트 상에서 그려지도록 한다.
 * 더이상 [view] 가 필요 없을 경우, [MapView] 에서 [view] 를 제거해 메모리 관리를 효율적으로 할 수 있도록 한다.
 */
internal fun MapView.startRenderingComposeView(
    view: AbstractComposeView,
    parentContext: CompositionContext,
): Closeable {
    val containerView = ensureContainerView()
    containerView.addView(view)
    view.apply {
        setParentCompositionContext(parentContext)
    }
    return Closeable { containerView.removeView(view) }
}

/**
 * [MapView] 에서 비트맵을 그려낼 [NoDrawContainerView] 를 반환하거나 없다면 생성해 반환함
 */
private fun MapView.ensureContainerView(): NoDrawContainerView =
    findViewById(R.id.maps_compose_nodraw_container_view)
        ?: NoDrawContainerView(context)
            .apply { id = R.id.maps_compose_nodraw_container_view }
            .also(::addView)

/**
 * 자식 view 가 실제로 그려지지 않도록 하기 위한 ViewGroup 구현체
 */
private class NoDrawContainerView(context: Context) : ViewGroup(context) {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) = Unit

    override fun dispatchDraw(canvas: Canvas) = Unit
}

/**
 * 안드로이드 Native View -> Bitmap 으로 변환
 */
internal fun View.toNativeBitmap(): Bitmap {
    val density = resources.displayMetrics.density
    if (measuredWidth <= 0 && measuredHeight <= 0) {
        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
        }

        measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    }

    layout(0, 0, measuredWidth, measuredHeight)

    val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)

    // xhdpi 사이즈를 만들기 위한 scale 지원
    val scaled = Bitmap.createScaledBitmap(
        bitmap,
        (bitmap.width / (density / 2)).toInt(),
        (bitmap.height / (density / 2)).toInt(),
        true,
    )
    bitmap.recycle()
    return scaled
}
