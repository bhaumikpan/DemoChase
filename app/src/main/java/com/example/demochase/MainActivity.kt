package com.example.demochase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.featureui.view.ListActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_list)
        button?.setOnClickListener {
            startActivity()
        }

    }

    private fun startActivity() {
        val intent = Intent(applicationContext, ListActivity::class.java)
        startActivity(intent)
    }
}