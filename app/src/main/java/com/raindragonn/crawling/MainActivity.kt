package com.raindragonn.crawling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raindragonn.crawling.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val chartAdapter by lazy { ChartAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()

        chartAdapter.submitList(getData())
    }

    private fun initViews() = with(binding) {
        rvChart.layoutManager = LinearLayoutManager(this@MainActivity)
        rvChart.adapter = chartAdapter
    }

    private fun getData() =
        intent?.getParcelableArrayListExtra<ChartModel>(SplashActivity.EXTRA_CHART)?.toList()

}