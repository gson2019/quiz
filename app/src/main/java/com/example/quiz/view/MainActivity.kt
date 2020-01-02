package com.example.quiz.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.R
import com.example.quiz.view.WelComeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WelComeFragment.newInstance())
            .commit()
    }
}
