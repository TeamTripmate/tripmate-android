package com.tripmate.android.feature.map.internal.node

import com.kakao.vectormap.label.Label

internal open class LabelNode(
    val overlay: Label,
    var onLabelClick: (Label) -> Unit,
) : MapContainerNode() {
    private var parent: LabelNode? = null

    override fun insertAt(index: Int, instance: MapNode) {
        // insert-top-down 에 대한 방어 로직
        check(parent != null) { "LabelNode should have max 2-depth hierarchy!" }
        val childLabelNode = checkNotNull(instance as? LabelNode) {
            "A parent LabelNode can only have a LabelNode as its child!"
        }
        // insert-bottom-up 에 대한 방어 로직
        check(childLabelNode.hasChildren()) { "LabelNode should have max 2-depth hierarchy!" }
        // insert
        childLabelNode.parent = this
        overlay.addShareTransform(childLabelNode.overlay)
        super.insertAt(index, instance)
    }

    override fun removeAt(index: Int, count: Int): List<MapNode> {
        return super.removeAt(index, count).onEach { instance ->
            val childLabelNode = checkNotNull(instance as? LabelNode) {
                "A parent LabelNode can only have a LabelNode as its child!"
            }
            overlay.removeShareTransform(childLabelNode.overlay)
            childLabelNode.parent = null
        }
    }

    override fun onRemoved() {
        super.onRemoved()
        overlay.remove()
    }
}
