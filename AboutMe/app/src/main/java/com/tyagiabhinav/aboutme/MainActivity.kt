package com.tyagiabhinav.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tyagiabhinav.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.doneBtn.setOnClickListener {
            onNicknameEntered(it)
        }

    }

    private fun onNicknameEntered(view: View) {
        // apply allows us to access views without repeatedly using "binding." like binding.doneBtn
        binding.apply {
            nickNameText.text = binding.nickNameEditText.text
            invalidateAll() // used to refresh binding with new UI data
            doneBtn.visibility = View.GONE
            nickNameEditText.visibility = View.GONE
            nickNameText.visibility = View.VISIBLE
        }

        // Hide Keyboard
        val ime = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ime.hideSoftInputFromWindow(view.windowToken,0)
    }

}