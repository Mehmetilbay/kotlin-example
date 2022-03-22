package com.example.kotlinexample.models

import com.example.kotlinexample.R

object FxList {

    fun getMockedFxList(): List<RecyclerViewItemModel> {
        val usd = RecyclerViewItemModel(R.drawable.currency_usd, "Amerikan Doları", "USD", 13.5892, 14.1278, 4.3)
        val eur = RecyclerViewItemModel(R.drawable.currency_eur, "Avrupa Para Birimi", "EUR", 15.4365, 16.2388, 1.3)
        val xau = RecyclerViewItemModel(R.drawable.currency_xau, "Altın", "XAU", 950.1589, 968.7948, -2.6)
        val gbp = RecyclerViewItemModel(R.drawable.currency_gbp, "İngiliz Sterlini", "GBP", 19.1448, 19.9248, -1.6)
        val aed = RecyclerViewItemModel(R.drawable.currency_aed, "Bae Dirhemi", "AED", 3.9048, 4.1648, 0.6)
        val aud = RecyclerViewItemModel(R.drawable.currency_aud, "Avustralya Doları", "AUD", 10.6948, 11.2948, 0.56)
        val cad = RecyclerViewItemModel(R.drawable.currency_cad, "Kanada Doları", "CAD", 11.4748, 12.0848, -0.3)
        val chf = RecyclerViewItemModel(R.drawable.currency_chf, "İsviçre Frangı", "CHF", 15.5948, 16.2048, 0.61)
        val dkk = RecyclerViewItemModel(R.drawable.currency_dkk, "Danimarka Kronu", "DKK", 2.1348, 2.2648, 0.18)
        val jpy = RecyclerViewItemModel(R.drawable.currency_jpy, "Japon Yeni", "DKK", 0.1214, 0.1273, -0.45)
        val kwd = RecyclerViewItemModel(R.drawable.currency_kwd, "Kuveyt Dinarı", "KWD", 47.8514, 49.7273, 0.37)
        val nok = RecyclerViewItemModel(R.drawable.currency_nok, "Norveç Kronu", "NOK", 1.6314, 1.7573, 0.58)
        val sar = RecyclerViewItemModel(R.drawable.currency_sar, "Suudi Arabistan Riyali", "SAR", 3.7614, 4.1373, 0.29)
        val sek = RecyclerViewItemModel(R.drawable.currency_sek, "İsveç Kronu", "SEK", 1.5114, 1.6373, 0.69)

        val itemList: ArrayList<RecyclerViewItemModel> = ArrayList()
        itemList.add(usd)
        itemList.add(eur)
        itemList.add(xau)
        itemList.add(gbp)
        itemList.add(aed)
        itemList.add(aud)
        itemList.add(cad)
        itemList.add(chf)
        itemList.add(dkk)
        itemList.add(jpy)
        itemList.add(kwd)
        itemList.add(nok)
        itemList.add(sar)
        itemList.add(sek)
        return itemList
    }
}