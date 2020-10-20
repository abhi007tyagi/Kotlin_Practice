package com.tyagiabhinav.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollBtn: Button = findViewById(R.id.rollBtn)
        val rollText: TextView = findViewById(R.id.rollText)

        rollBtn.setOnClickListener {
            Toast.makeText(this, "Yahoo, I rolled", Toast.LENGTH_LONG).show()
        }
    }
}