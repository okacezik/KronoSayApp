package com.okancezik.saat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun counter(view : View){
        val intent = Intent(this , CounterActivity::class.java)
        startActivity(intent)
    }

    fun chronometer(view: View){
        val intent = Intent(this , ChronometerActivity::class.java)
        startActivity(intent)
    }
}