package com.raindragonn.crawling

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.junit.Test

// Created by raindragonn on 2021/06/01.

class CrawlingTest {
    companion object {
        private const val MELON_CHART_URL = "https://www.melon.com/chart/index.htm"
    }


    @Test
    fun test() {
        Jsoup.connect(MELON_CHART_URL).get().let { doc ->
            val elements = doc.select("#frm > div > table > tbody")

            val list: MutableList<ChartModel> = mutableListOf()

            (1..100).forEach { index ->
                val item = elements.select("tr:nth-child($index)")
                val title =
                    item.select("td:nth-child(4) > div > div > div.ellipsis.rank01 > span > a")
                        .text()
                val imgUrl = item.select("td:nth-child(2) > div > a > img").attr("src")
                val artist =
                    item.select("td:nth-child(4) > div > div > div.ellipsis.rank02 > a").text()

                list.add(
                    ChartModel(
                        artist, title, imgUrl
                    )
                )
            }
        }
    }
}