package com.okancezik.saat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_chronometer.*

class ChronometerActivity : AppCompatActivity() {

    var number : Int? = null
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer)

        startbutton.visibility = View.VISIBLE
        stopbutton.visibility = View.INVISIBLE
        continuebutton.visibility = View.INVISIBLE
        restartbutton.visibility = View.INVISIBLE
        number = 0
    }

    fun mainPage(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun counterPage(view: View){
        val intent = Intent(this, CounterActivity::class.java)
        startActivity(intent)
    }

    fun start(view: View){
        startbutton.visibility = View.INVISIBLE
        stopbutton.visibility = View.VISIBLE
        continuebutton.visibility = View.INVISIBLE
        restartbutton.visibility = View.INVISIBLE
        runnable = object : Runnable{
            override fun run() {
                timeValueText.text = "Time : ${number}"
                number = number!! + 1
                handler.postDelayed(runnable, 1000) //specify runnable's  periot
            }
        }
        handler.post(runnable)  //runnable run
    }

    fun continueTime(view : View){
        handler.post(runnable)  //runnable run
        continuebutton.visibility = View.INVISIBLE
        stopbutton.visibility = View.VISIBLE
        restartbutton.visibility = View.VISIBLE
        startbutton.visibility = View.INVISIBLE
    }

    fun stop(view: View){
        handler.removeCallbacks(runnable) //runnable stop
        startbutton.visibility = View.INVISIBLE
        continuebutton.visibility = View.VISIBLE
        restartbutton.visibility = View.VISIBLE
        stopbutton.visibility = View.INVISIBLE
    }

    fun restart(view: View){
        number = 0
        start(view)
    }
}