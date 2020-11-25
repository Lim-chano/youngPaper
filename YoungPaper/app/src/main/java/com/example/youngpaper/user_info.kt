package com.example.youngpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user_info.*

class user_info : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    var userid =""
    var who =""
    var userbirth=""
    var useremail=""
    var userinterest=""
    var username=""
    var userphonenumber=""
    var userpw=""
    var usernickname=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        database = FirebaseDatabase.getInstance().reference

        if(intent.hasExtra("userid")){
            userid = intent.getStringExtra("userid")

        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("who")){
            who = intent.getStringExtra("who")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("userbirth")){
            userbirth = intent.getStringExtra("userbirth")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("useremail")){
            useremail = intent.getStringExtra("useremail")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("userinterest")){
            userinterest = intent.getStringExtra("userinterest")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("username")){
            username = intent.getStringExtra("username")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("userphonenumber")){
            userphonenumber = intent.getStringExtra("userphonenumber")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("userpw")){
            userpw = intent.getStringExtra("userpw")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        if(intent.hasExtra("usernickname")){
            usernickname = intent.getStringExtra("usernickname")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }


        user_id.setText(userid)
        user_id2.setText(username)
        user_id3.setText(userpw)
        user_id4.setText(usernickname)
        user_id5.setText(useremail)
        user_id6.setText(userphonenumber)
        user_id7.setText(userbirth)



    }

}