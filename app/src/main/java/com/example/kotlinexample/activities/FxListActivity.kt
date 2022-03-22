package com.example.kotlinexample.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample.R
import com.example.kotlinexample.databinding.ActivityFxListBinding
import com.example.kotlinexample.databinding.ViewRecyclerviewItemBinding
import com.example.kotlinexample.models.FxList
import com.example.kotlinexample.models.RecyclerViewItemModel


class FxListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFxListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFxListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        /*
        binding.recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        binding.recyclerview.layoutManager = GridLayoutManager(this@FxListActivity, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerview.adapter = RecyclerViewAdapter(FxList.getMockedFxList() as ArrayList<RecyclerViewItemModel>)
         */

        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(this@FxListActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = RecyclerViewAdapter(FxList.getMockedFxList() as ArrayList<RecyclerViewItemModel>)
        }

        /*
        post(url, requestModel, FxListResponseModel::class.java) { response ->
            initView(response)
        }
         */
    }

    private fun startFxDetailActivity(selectedIndex: Int) {
        Intent(this, FxDetailActivity::class.java).apply {
            putExtra("selectedIndex", selectedIndex)
            startActivity(this)
        }
    }

    private inner class RecyclerViewAdapter(val fxList: ArrayList<RecyclerViewItemModel>?) :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewItemViewHolder>() {

        private inner class RecyclerViewItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var binding: ViewRecyclerviewItemBinding = ViewRecyclerviewItemBinding.bind(view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.RecyclerViewItemViewHolder {
            return RecyclerViewItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_recyclerview_item,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: RecyclerViewItemViewHolder, position: Int) {
            holder.binding.apply {
                /*
                if (fxList != null && fxList[position] != null) {
                    recyclerviewItemCurrencyImage.setImageResource(fxList[position].currencyImage)
                }
                 */

                fxList?.get(position)?.let { item ->
                    recyclerviewItemCurrencyImage.setImageResource(item.currencyImage)
                    recyclerviewItemCurrencyName.text = item.currencyName
                } ?: run {
                    // fxList or fxListItem is null
                }
            }

            holder.itemView.setOnClickListener {
                startFxDetailActivity(position)
            }
        }

        override fun getItemCount(): Int {
            return fxList?.size ?: 0
        }
    }
}
