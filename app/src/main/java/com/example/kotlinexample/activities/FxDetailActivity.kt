package com.example.kotlinexample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexample.R
import com.example.kotlinexample.databinding.ActivityFxDetailBinding
import com.example.kotlinexample.models.FxChart
import com.example.kotlinexample.models.FxList
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class FxDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFxDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFxDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedIndex = intent.getIntExtra("selectedIndex", 0)

        setCurrentCurrencyDetail(selectedIndex)
        setLineChart(selectedIndex)
    }

    private fun setCurrentCurrencyDetail(selectedIndex: Int) {
        val selectedItem = FxList.getMockedFxList()[selectedIndex]

        binding.apply {
            fxDetailCurrencyImage.setImageResource(selectedItem.currencyImage)
            fxDetailCurrencyCode.text = selectedItem.currencyCode

            fxDetailBankBuy.apply {
                fxCurrentInfoText.text = getString(R.string.fx_detail_bank_buy)
                fxCurrentInfoValue.text = selectedItem.buyRate.toString()
            }

            fxDetailBankSell.apply {
                fxCurrentInfoText.text = getString(R.string.fx_detail_bank_sell)
                fxCurrentInfoValue.text = selectedItem.sellRate.toString()
            }

            fxDetailDailyChange.apply {
                fxCurrentInfoText.text = getString(R.string.fx_detail_daily_change)
                fxCurrentInfoValue.text = selectedItem.dailyChange.toString()
            }

            when {
                selectedItem.dailyChange.compareTo(0) > 0 -> {
                    fxDetailChangeImage.setImageResource(R.drawable.arrow_up)
                }
                selectedItem.dailyChange.compareTo(0) < 0 -> {
                    fxDetailChangeImage.setImageResource(R.drawable.arrow_down)
                }
                else -> {
                    fxDetailChangeImage.setImageResource(R.drawable.stable)
                }
            }
        }
    }

    private fun setLineChart(selectedIndex: Int) {

        // https://github.com/PhilJay/MPAndroidChart

        binding.lineChart.apply {
            legend.isEnabled = false
            description.isEnabled = false

            isDoubleTapToZoomEnabled = false
            setPinchZoom(false)
            isScaleXEnabled = false
            isScaleYEnabled = false

            axisRight.apply {
                isEnabled = false
                setDrawLabels(false)
            }

            val fxChartData = FxChart.getMockedChartData()
            xAxis.apply {
                granularity = 1f
                isGranularityEnabled = true
                textSize = 10f
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                labelCount = (fxChartData[selectedIndex].size).coerceAtMost(5)

                val dateList: ArrayList<String> = ArrayList()
                fxChartData[selectedIndex].forEach { selectedItem ->
                    dateList.add(selectedItem.rateDate)
                }

                valueFormatter = IndexAxisValueFormatter(dateList)
            }

            val lineChartEntries: ArrayList<Entry> = ArrayList()
            fxChartData[selectedIndex].forEachIndexed { index, selectedItem ->
                lineChartEntries.add(Entry(index.toFloat(), selectedItem.averageRate.toFloat()))
            }

            val dataSet = LineDataSet(lineChartEntries, getString(R.string.fx_detail_title))
            dataSet.apply {
                fillDrawable = getDrawable(R.drawable.gradient_blue)
                setDrawFilled(true)
                valueTextSize = 10f
            }

            clear()
            data = LineData(dataSet)
            notifyDataSetChanged()
        }
    }
}
