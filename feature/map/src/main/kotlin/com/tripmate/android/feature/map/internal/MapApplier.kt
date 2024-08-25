package com.tripmate.android.feature.map.internal

import androidx.compose.runtime.AbstractApplier
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.MapView
import com.kakao.vectormap.label.Label
import com.tripmate.android.feature.map.internal.node.LabelNode
import com.tripmate.android.feature.map.internal.node.MapContainerNode
import com.tripmate.android.feature.map.internal.node.MapNode
import com.tripmate.android.feature.map.internal.node.RootMapNode

/**
 * [MapNode] 를 관리하는 [AbstractApplier].
 *
 * Composition 에 [MapNode] 트리의 관리에 대한 명세를 전달한다.
 */
internal class MapApplier(
    val map: KakaoMap,
    internal val mapView: MapView,
) : AbstractApplier<MapNode>(RootMapNode()) {

    private val labelNodes = mutableMapOf<Label, LabelNode>()

    init {
        initOnClickListeners()
    }

    /**
     * 현재 MapNode 트리의 구조는 ComposeNode 에 의해 factory 로 생성된 MapRootNode 가 Root 인 Tree 이다.
     *
     * Parent 노드는 고정된 채로 내부적으로 노드를 관리하는 방식을 차용하기 때문에 bottom up 방식이 좀 더 적은 호출로 노드 재구성을 달성할 수 있음.
     * Child 노드가 추가되는 경우, 두 방식의 Compose 통지 동작은 아래와 같다.
     * - top down 의 경우 새로 추가된 Node 의 parent 및 Root 에 통지
     * - bottom up 의 경우 새로 추가된 Node 의 parent 에만 통지
     *
     * 즉 현 노드의 구성은 고정된 Root Node 의 child node 들 ( UI ComposeNode ) insertion 과 deletion 만이 발생하므로
     * top down 방식이 아닌 bottom up 방식을 통해 구현함으로서 성능 개선을 달성할 수 있음.
     *
     * @see [insertBottomUp]
     */
    override fun insertBottomUp(index: Int, instance: MapNode) {
        checkNotNull(current as? MapContainerNode).insertAt(index = index, instance = instance)
        when (instance) {
            is LabelNode -> labelNodes[instance.overlay] = instance
            else -> Unit
        }
    }

    // Don't use because of performance issues !!
    override fun insertTopDown(index: Int, instance: MapNode) = Unit

    override fun move(from: Int, to: Int, count: Int) {
        checkNotNull(current as? MapContainerNode).move(from = from, to = to, count = count)
    }

    override fun remove(index: Int, count: Int) {
        checkNotNull(current as? MapContainerNode).removeAt(index = index, count = count)
            .forEach { instance ->
                when (instance) {
                    is LabelNode -> labelNodes.remove(instance.overlay)
                    else -> Unit
                }
            }
    }

    override fun onClear() {
        labelNodes.clear()
    }

    private fun initOnClickListeners() {
        map.setOnLabelClickListener { _, _, label ->
            labelNodes[label]?.run { onLabelClick(label) }
        }
    }
}
