package com.okancezik.saat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {

    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        startbutton.visibility = View.VISIBLE
        stopbutton.visibility = View.INVISIBLE
        restartbutton.visibility = View.INVISIBLE
    }

    fun chronometerPage(view: View) {
        val intent = Intent(this, ChronometerActivity::class.java)
        startActivity(intent)
    }

    fun mainPage(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun start(view: View) {
        var startValue = startValueInput.text.toString().toLongOrNull()

        if(startValue == null){
            timeText.text = "lütfen başlangıç değeri giriniz..."
        }else{
            startbutton.visibility = View.INVISIBLE
            stopbutton.visibility = View.VISIBLE
            runnable = object : Runnable{
                override fun run() {
                    if(startValue!! >= 0){
                        timeText.text = "Time : ${startValue}"
                        startValue = startValue!! - 1;
                    }else{
                        timeText.text = "Zaman Bitti!!!"
                        startbutton.visibility = View.VISIBLE
                        stopbutton.visibility = View.INVISIBLE
                        restartbutton.visibility = View.INVISIBLE
                        handler.removeCallbacks(runnable)
                    }
                    handler.postDelayed(runnable,1000)
                }
            }
            handler.post(runnable)
        }
    }

    fun continueTime(view : View){
        handler.post(runnable)
        stopbutton.visibility = View.VISIBLE
        startbutton.visibility = View.INVISIBLE
        restartbutton.visibility = View.INVISIBLE
    }

    fun stop(view : View){
        handler.removeCallbacks(runnable)
        stopbutton.visibility = View.INVISIBLE
        startbutton.visibility = View.INVISIBLE
        restartbutton.visibility = View.VISIBLE
    }
}