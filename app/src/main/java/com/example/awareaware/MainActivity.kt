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

        // Initialize buttons with error handling
        val newScanButton = findViewById<Button?>(R.id.newScanButton)
        val previousScanButton = findViewById<Button?>(R.id.previousScanButton)
        val exitButton = findViewById<Button?>(R.id.exitButton)

        if (newScanButton != null) {
            newScanButton.setOnClickListener {
                val intent = Intent(this, NewScanActivity::class.java)
                startActivity(intent)
            }
        } else {
            Log.e("MainActivity", "newScanButton is null")
        }

        if (previousScanButton != null) {
            previousScanButton.setOnClickListener {
                val intent = Intent(this, PreviousScansActivity::class.java)
                startActivity(intent)
            }
        } else {
            Log.e("MainActivity", "previousScanButton is null")
        }

        if (exitButton != null) {
            exitButton.setOnClickListener {
                finish()
            }
        } else {
            Log.e("MainActivity", "exitButton is null")
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
