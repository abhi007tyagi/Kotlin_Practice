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
    // Instance of MyName data class.
    private val myName: MyName = MyName("Abhinav Tyagi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        binding.doneBtn.setOnClickListener {
            onNicknameEntered(it)
        }

    }

    private fun onNicknameEntered(view: View) {
        // apply allows us to access views without repeatedly using "binding." like binding.doneBtn
        binding.apply {
            myName?.nickName = nickNameEditText.text.toString()
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