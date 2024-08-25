package com.tripmate.android.feature.map.internal.node

/**
 * [androidx.compose.runtime.AbstractApplier] 에 의해 관리될 노드의 명세
 *
 * Nested 한 구조를 관리할 수 있도록 내부적으로 자식노드 정보를 가지고 있는다.
 * 자식 노드에 대한 삽입/삭제/이동 등의 관리를 제공한다.
 */
internal open class MapContainerNode : MapNode {
    private val children = mutableListOf<MapNode>()

    /**
     * 특정 위치에 자식 노드를 삽입한다.
     *
     * @param index 노드를 삽입할 index
     * @param instance 삽입될 노드
     */
    open fun insertAt(index: Int, instance: MapNode) {
        children.add(index, instance)
        instance.onAttached()
    }

    /**
     * 특정 위치의 자식 노드를 삭제한다.
     *
     * @param index 삭제할 노드의 index
     * @param count
     */
    open fun removeAt(index: Int, count: Int): List<MapNode> {
        require(count >= 0)
        return (index until index + count).map {
            children.removeAt(index).also { it.onRemoved() }
        }
    }

    /**
     * 자식 노드의 위치를 이동시킨다.
     *
     * @param from 이동시킬 노드 그룹의 시작 index
     * @param to 이동 후에 위치할 노드 그룹의 시작 index
     * @param count 이동시킬 노드 그룹의 개수
     */
    open fun move(from: Int, to: Int, count: Int) {
        if (from == to) return
        @Suppress("ForEachOnRange")
        (0 until count).forEach { index ->
            val fromIndex = if (from > to) from + index else from
            val toIndex = if (from > to) to + index else to + count - 2
            val child = children.removeAt(fromIndex)
            children.add(toIndex, child)
        }
    }

    /**
     * 노드가 제거될 때 수행되는 함수
     *
     * 자식 노드들 또한 제거되어야 하므로, 자식 노드의 제거 callback 을 nested 하게 호출해 이를 보장할 수 있도록 한다.
     */
    override fun onRemoved() {
        children.forEach { node -> node.onRemoved() }
        children.clear()
    }

    /**
     * 자식 노드가 있는지 확인하는 함수
     *
     * @return 자식 노드 보유 여부
     */
    fun hasChildren(): Boolean = children.isNotEmpty()
}

internal class RootMapNode : MapContainerNode()
