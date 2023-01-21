package com.example.featureui.view

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.featureui.R
import com.example.featureui.util.hide
import com.example.featureui.util.show
import com.example.featureui.viewmodel.SchoolDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailsActivity: AppCompatActivity() {

    private val detailViewModel: SchoolDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.school_details)

        val loaderView = findViewById<ProgressBar>(R.id.loader)
        val dataTxtView = findViewById<TextView>(R.id.txt_school_detail)

        val schoolId = intent.getStringExtra("school_id") ?: "NO_ID"

        loaderView.show()
        detailViewModel.getData(schoolId)
        detailViewModel.schoolData.observe(
            this
        ) { school ->
            loaderView.hide()
            dataTxtView?.text = "Name: ${school.school_name}\n" +
                    "Number of Sat test takers:  ${school.num_of_sat_test_takers}\n" +
                    "Sat critical reading avg score: ${school.sat_critical_reading_avg_score}\n" +
                    "Sat math avg score: ${school.sat_math_avg_score},\n" +
                    "Sat writing avg score: ${school.sat_writing_avg_score}"
        }
    }
}