package com.example.kirichenko_lessons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Office(
    val location: String,
    val flag: Int,
    val numberOfWorkers: Int,
    val country: Country,
    val viewType: Int
)
