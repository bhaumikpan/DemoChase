package com.example.featureui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.featuredomian.models.School
import com.example.featureui.adapter.CustomAdapter
import com.example.featureui.util.hide
import com.example.featureui.util.show
import com.example.featureui.R as FeatureR
import com.example.featureui.viewmodel.SchoolListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity: AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val listViewModel: SchoolListViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomAdapter

    private val data = ArrayList<School>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FeatureR.layout.list_activity)

        val loaderView = findViewById<ProgressBar>(FeatureR.id.loader)

        adapter = CustomAdapter(data, this)

        recyclerView = findViewById(FeatureR.id.schoolListView)

        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter



        loaderView.show()
        listViewModel.getData()
        listViewModel.dataList.observe(this) { response ->
            when (response.isNotEmpty()) {
                true -> {
                    // display data
                    loaderView.hide()
                    data.addAll(response)
                    displayData(data)
                }
                else -> {
                    // error case
                    loaderView.hide()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayData(data: List<School>) {
        recyclerView.show()
        adapter.updateDataList(data)
    }

    private fun startDetailActivity(item: School) {
        val intent = Intent(applicationContext, SchoolDetailsActivity::class.java)
        intent.putExtra("school_id", item.dbn)
        startActivity(intent)
    }

    override fun onItemClick(position: Int) {
        startDetailActivity(data[position])
    }

}