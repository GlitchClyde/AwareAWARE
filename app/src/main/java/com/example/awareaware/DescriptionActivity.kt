package com.example.awareaware

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val photoPath = intent.getStringExtra("photo_path")

        val imageView = findViewById<ImageView>(R.id.image_view)

        if (photoPath != null) {
            val bitmap = BitmapFactory.decodeFile(photoPath)
            imageView.setImageBitmap(bitmap)
        } else {
            // Handle error - unable to load photo
        }
    }
}
