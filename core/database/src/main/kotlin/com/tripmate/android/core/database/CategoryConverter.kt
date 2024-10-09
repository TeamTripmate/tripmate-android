package com.tripmate.android.core.database

import androidx.room.TypeConverter
import com.tripmate.android.core.database.entity.Category
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CategoryConverter {
    private val json = Json

    @TypeConverter
    fun fromCategory(category: Category): String {
        return json.encodeToString(category)
    }

    @TypeConverter
    fun toCategory(categoryString: String): Category {
        return json.decodeFromString(categoryString)
    }
}
