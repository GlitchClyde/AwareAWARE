package com.example.awareaware

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import java.util.Locale

class MainActivity : Activity(), TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        // Initialize buttons
        val newScanButton = findViewById<Button>(R.id.btn_scan)
        val previousScanButton = findViewById<Button>(R.id.btn_view_scans)
        val exitButton = findViewById<Button>(R.id.btn_exit)

        newScanButton?.apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, NewScanActivity::class.java)
                startActivity(intent)
            }
            setOnLongClickListener {
                speakText("Start a new scan")
                true
            }
        } ?: Log.e("MainActivity", "btn_scan is null")

        previousScanButton?.apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, PreviousScansActivity::class.java)
                startActivity(intent)
            }
            setOnLongClickListener {
                speakText("View previous scans")
                true
            }
        } ?: Log.e("MainActivity", "btn_view_scans is null")

        exitButton?.apply {
            setOnClickListener {
                finish()
            }
            setOnLongClickListener {
                speakText("Exit the application")
                true
            }
        } ?: Log.e("MainActivity", "btn_exit is null")
    }

    private fun speakText(text: String) {
        if (::tts.isInitialized) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported")
            } else {
                // TTS Initialization successful
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
