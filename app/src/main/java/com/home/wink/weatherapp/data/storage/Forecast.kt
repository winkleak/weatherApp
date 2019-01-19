package com.home.wink.weatherapp.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "forecasts")
class Forecast(
    @PrimaryKey
    val toTranslate: String,
    val translation: String,
    val dateAdded: Date?,
    val imageUrl: String,
    val learningLevel: Int
)
