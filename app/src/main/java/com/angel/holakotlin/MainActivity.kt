package com.angel.holakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts = TextToSpeech(this,this)
        findViewById<Button>(R.id.btnPlay).setOnClickListener{
            speak()
        }
    }


    fun speak() {
        var message = findViewById<EditText>(R.id.etMessage).text.toString()
        if (message.isEmpty()){
            message = "Escribe alguna vaina"
        }
        findViewById<TextView>(R.id.textview).text = message
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            findViewById<TextView>(R.id.textview).text = "listo"
        }else{
            findViewById<TextView>(R.id.textview).text = "No disponible :("
        }
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}