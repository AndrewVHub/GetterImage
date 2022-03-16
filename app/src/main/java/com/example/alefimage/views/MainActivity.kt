package com.example.alefimage.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alefimage.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AlefImage)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}