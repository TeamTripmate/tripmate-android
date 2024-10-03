package com.tripmate.android.core.common.utils

import com.tripmate.android.core.designsystem.R

fun getCharacterName(characterId: String): String {
    return when (characterId) {
        "PENGUIN" -> "여행가 아기펭귄"
        "HONEYBEE" -> "여행가 아기꿀벌"
        "ELEPHANT" -> "여행가 아기코끼리"
        "DOLPHIN" -> "여행가 아기돌고래"
        "TURTLE" -> "여행가 아기거북이"
        else -> "여행가 아기판다"
    }
}

fun getCharacterImage(characterId: String): Int {
    return when (characterId) {
        "PENGUIN" -> R.drawable.img_character_01
        "HONEYBEE" -> R.drawable.img_character_02
        "ELEPHANT" -> R.drawable.img_character_03
        "DOLPHIN" -> R.drawable.img_character_04
        "TURTLE" -> R.drawable.img_character_05
        else -> R.drawable.img_character_06
    }
}
