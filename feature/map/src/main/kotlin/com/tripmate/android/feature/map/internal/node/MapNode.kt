package com.tripmate.android.feature.map.internal.node

/**
 * 카카오 맵 컴포저블의 노드 명세
 *
 * - [onAttached] : 노드가 삽입될 때 실행될 행동을 정의
 * - [onRemoved] : 노드가 제거될 때 실행될 행동을 정의
 */
internal interface MapNode {
    fun onAttached() = Unit
    fun onRemoved() = Unit
}
