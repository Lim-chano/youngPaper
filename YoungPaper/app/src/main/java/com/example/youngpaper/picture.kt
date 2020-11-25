package com.example.youngpaper

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_picture.*

class picture : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val storage = FirebaseStorage.getInstance("gs://youngpaper-7d36b.appspot.com")
        val storageRef = storage.reference
        storageRef.child("images/imgimg").downloadUrl
            .addOnSuccessListener { uri -> //이미지 로드 성공시
                Glide.with(applicationContext)
                    .load(uri)
                    .placeholder(R.drawable.exit)
                    .error(R.drawable.exit)
                    .into(imageView2)
            }.addOnFailureListener { //이미지 로드 실패시
                Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
            }
    }
}