package com.raindragonn.crawling

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Created by raindragonn on 2021/06/01.

@Parcelize
data class ChartModel(
    val id : Long,
    val artist: String,
    val title: String,
    val imgUrl: String
): Parcelable