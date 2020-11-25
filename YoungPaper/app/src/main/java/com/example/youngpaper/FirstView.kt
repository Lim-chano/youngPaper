package com.example.youngpaper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_first_view.*
import kotlinx.android.synthetic.main.activity_login.*

class FirstView : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    var script_list: ArrayList<String>  = ArrayList()
    var userid = ""
    var who = ""
    var userbirth=""
    var useremail=""
    var userinterest=""
    var username=""
    var userphonenumber=""
    var userpw=""
    var usernickname=""

    val newsList = arrayListOf<News>(
        News("", "A", "aaaa"),
        News("", "B", "bbbb"),
        News("", "C", "cccc"),
        News("", "D", "dddd"),
        News("", "E", "eeee"),
        News("", "F", "ffff"),
        News("", "G", "gggg"),
        News("", "H", "hhhh"),
        News("", "I", "iiii"),
        News("", "J", "jjjj")

    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_view)
        database = FirebaseDatabase.getInstance().reference

        //script list 저장
        newsList.add(News("","K","kkkk"))



        if(intent.hasExtra("userid")){
            userid = intent.getStringExtra("userid")
            Toast.makeText(this, userid, Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            userid = "id"
        }
        if(intent.hasExtra("who")){
            who = intent.getStringExtra("who")
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            who = "user"
        }
        user_name.setText(userid+"님 환영합니다.")
        userSetting(who, userid)
        titleImg.setOnClickListener{
            val intent = Intent(this, write_script::class.java)
            intent.putExtra("userid",userid)
            startActivity(intent)
        }
        menuImg.setOnClickListener{
            drawer.openDrawer(GravityCompat.START)
        }
        searchImg.setOnClickListener{
            // 검색화면으로 넘어가기
        }
        exit.setOnClickListener{
            drawer.closeDrawer(GravityCompat.START)
        }
        user.setOnClickListener{

            val intent = Intent(this, user_info::class.java)
            intent.putExtra("userid",userid)
            intent.putExtra("who",who)
            intent.putExtra("userbirth",userbirth)
            intent.putExtra("useremail",useremail)
            intent.putExtra("userinterest",userinterest)
            intent.putExtra("username",username)
            intent.putExtra("userphonenumber",userphonenumber)
            intent.putExtra("userpw",userpw)
            intent.putExtra("usernickname",usernickname)
            startActivity(intent)
        }
        setting.setOnClickListener{
            // 설정 화면으로 넘어가기
        }

        val newsAdapter = RecyclerViewAdapter()
        newsAdapter.addNews(newsList)
        newsRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        newsRecyclerView.apply {
            adapter = newsAdapter
            setHasFixedSize(true)
        }

    }

    private fun userSetting(who: String, userid: String){
        database.child(who).child(userid).child("user_birth")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    userbirth = snapshot.getValue().toString()
                }
            })
        database.child(who).child(userid).child("user_email")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    useremail = snapshot.getValue().toString()
                }
            })
        database.child(who).child(userid).child("user_interest")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    userinterest = snapshot.getValue().toString()
                }
            })
        database.child(who).child(userid).child("user_name")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    username = snapshot.getValue().toString()
                }
            })
        database.child(who).child(userid).child("user_phonenumber")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    userphonenumber = snapshot.getValue().toString()
                }
            })
        database.child(who).child(userid).child("user_pw")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    userpw = snapshot.getValue().toString()
                }
            })
        database.child(who).child(userid).child("user_nickname")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    usernickname = snapshot.getValue().toString()
                    println(usernickname)
                }
            })

    }

    private fun makeList() {
        database.child("script").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("failed")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(messageData in snapshot.getChildren()) {
                    script_list.add(messageData.key.toString())
                }
            }
        })
        println(result("id","key"))

    }
    fun result(scriptid: String, key: String): String {
        var result=""
        database.child("script").child(scriptid).child(key)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    result = snapshot.getValue().toString()
                }
            })
        return result
    }
}