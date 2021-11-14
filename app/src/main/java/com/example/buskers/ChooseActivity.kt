package com.example.buskers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        busker.setOnClickListener { startActivity(Intent(this, ListActivity::class.java)) }
        listener.setOnClickListener { startActivity(Intent(this, ListActivity::class.java)) }
    }
}