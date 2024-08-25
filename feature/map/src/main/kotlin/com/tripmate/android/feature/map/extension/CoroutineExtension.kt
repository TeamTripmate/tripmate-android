package com.tripmate.android.feature.map.extension

import androidx.compose.runtime.Composition
import kotlinx.coroutines.awaitCancellation

/**
 * Composition 의 수행이 끝나면 폐기함.
 *
 * 첫 Composition 이 Initialize 될 때 수행되며 이때 맵 객체를 초기화해 가져오는 역할을 수행한다.
 */
internal suspend inline fun disposingComposition(factory: () -> Composition) {
    val composition = factory()
    try {
        awaitCancellation()
    } finally {
        composition.dispose()
    }
}
