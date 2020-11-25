package com.example.youngpaper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var u: DatabaseReference
    var id_list: ArrayList<String>  = ArrayList()
    var a = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("kkk")
        text.setOnClickListener{
            writeNewUser("1","aaaa")
            val intent = Intent(this, FirstView::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            readData()
        }
        button2.setOnClickListener{
            val intent = Intent(this, newsView::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id_list",id_list)
            startActivity(intent)
        }
        fix4.setOnClickListener{

            database.child("user").child("id").child("user_pw").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val b = snapshot.getValue()
                    println(b)
                }
            })
        }
    }

    private fun writeNewUser(number: String, value: String) {
        database = FirebaseDatabase.getInstance().reference
        database.child(number).setValue(value)


    }


    private fun readData(){
        database.child("user").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("failed")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(messageData in snapshot.getChildren()) {
                    id_list.clear()
                    id_list.add(messageData.key.toString())
                }
                println(id_list)
            }
        })
    }
}


