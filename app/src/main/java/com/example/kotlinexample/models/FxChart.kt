package com.example.kotlinexample.models

object FxChart {

    fun getMockedChartData(): List<ArrayList<ChartItemModel>> {

        // USD Data
        val usd1 = ChartItemModel(12.5689, "14/03")
        val usd2 = ChartItemModel(13.4729, "15/03")
        val usd3 = ChartItemModel(11.5420, "16/03")
        val usd4 = ChartItemModel(13.8907, "17/03")
        val usd5 = ChartItemModel(13.9848, "18/03")

        //EUR Data
        val eur1 = ChartItemModel(14.5689, "14/03")
        val eur2 = ChartItemModel(15.4729, "15/03")
        val eur3 = ChartItemModel(15.5420, "16/03")
        val eur4 = ChartItemModel(14.8907, "17/03")
        val eur5 = ChartItemModel(15.9848, "18/03")

        // XAU Data
        val xau1 = ChartItemModel(935.5689, "14/03")
        val xau2 = ChartItemModel(942.4729, "15/03")
        val xau3 = ChartItemModel(940.5420, "16/03")
        val xau4 = ChartItemModel(957.8907, "17/03")
        val xau5 = ChartItemModel(955.9848, "18/03")

        val usdDataList: ArrayList<ChartItemModel> = ArrayList()
        usdDataList.add(usd1)
        usdDataList.add(usd2)
        usdDataList.add(usd3)
        usdDataList.add(usd4)
        usdDataList.add(usd5)

        val eurDataList: ArrayList<ChartItemModel> = ArrayList()
        eurDataList.add(eur1)
        eurDataList.add(eur2)
        eurDataList.add(eur3)
        eurDataList.add(eur4)
        eurDataList.add(eur5)

        val xauDataList: ArrayList<ChartItemModel> = ArrayList()
        xauDataList.add(xau1)
        xauDataList.add(xau2)
        xauDataList.add(xau3)
        xauDataList.add(xau4)
        xauDataList.add(xau5)

        val chartDataList: ArrayList<ArrayList<ChartItemModel>> = ArrayList()
        chartDataList.add(usdDataList)
        chartDataList.add(eurDataList)
        chartDataList.add(xauDataList)

        return chartDataList
    }
}