package com.raindragonn.crawling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val MELON_CHART_URL = "https://www.melon.com/chart/index.htm"
        const val EXTRA_CHART = "EXTRA_CHART"
    }

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mainScope.launch {
            val list = getChartList()
            startMain(list.await())
        }
    }

    private fun startMain(list: ArrayList<ChartModel>) {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
            putParcelableArrayListExtra(EXTRA_CHART, list)
        })
        finish()
    }

    private fun getChartList() = mainScope.async(Dispatchers.IO) {
        Jsoup.connect(MELON_CHART_URL).get().let { doc ->
            val elements = doc.select("#frm > div > table > tbody")
            val list = arrayListOf<ChartModel>()
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
                        index.toLong(), artist, title, imgUrl
                    )
                )
            }
            list
        }
    }
}