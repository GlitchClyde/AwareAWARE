package com.example.awareaware

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class NewScanActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_scan)

        tts = TextToSpeech(this, this)

        val retakeButton = findViewById<Button>(R.id.retake_button)
        val doneButton = findViewById<Button>(R.id.done_button)

        retakeButton.setOnLongClickListener {
            speakText("Please press to retake the photo.")
            true
        }

        doneButton.setOnLongClickListener {
            speakText("Please press Done to proceed.")
            true
        }
    }

    private fun speakText(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }
}