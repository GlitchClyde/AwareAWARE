package com.example.awareaware

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val photoPath = intent.getStringExtra("photo_path")
        if (photoPath == null) {
            // Log error or show a toast message
            finish()
            return
        }

        val imageView = findViewById<ImageView>(R.id.image_view)
        val descriptionEditText = findViewById<EditText>(R.id.description_edit_text)

        val bitmap = BitmapFactory.decodeFile(photoPath)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
        } else {
            // Log error or show a toast message
            finish()
        }
    }
}
