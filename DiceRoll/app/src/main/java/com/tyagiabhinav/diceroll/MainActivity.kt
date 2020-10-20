package com.tyagiabhinav.diceroll

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// "late init" because we don't want it to be nullable and check for not null everytime.
// We are telling Kotlin that we will initialize the variable after we get it in onCreate()
// var because val is immutable
lateinit var diceImage: ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.diceImage)
        val rollBtn: Button = findViewById(R.id.rollBtn)
        rollBtn.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val diceRoll = when ((Math.random() * 6 + 1).toInt()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }
        diceImage.setImageResource(diceRoll)
    }
}