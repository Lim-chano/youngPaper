package com.example.youngpaper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    var userid =""
    var id_list: ArrayList<String>  = ArrayList()
    var id_confirm = false
    var pw_confirm = false
    var who =""
    var user_cpw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        database = FirebaseDatabase.getInstance().reference

        //로그인 버튼 클릭 시
        loginButton.setOnClickListener {
            id_confirm =false
            pw_confirm=false
            var edit_id = edtLoginID.text.toString()
            var edit_pw = edtLoginPW.text.toString()
            //라디오 버튼 확인
            when (radio_group_login.checkedRadioButtonId) {
                R.id.user_radioButton_login -> who = "user"
                R.id.writer_radioButton_login -> who = "writer"
            }
            //아이디 맞는지 틀린지 확인
            confirm_ID(who, edit_id)
            if(id_confirm==true){
                confirm_PW(who, edit_pw)
                if(pw_confirm==true){
                    userid = edit_id
                    val intent = Intent(this, FirstView::class.java)
                    intent.putExtra("userid",userid)
                    intent.putExtra("who", who)
                    startActivity(intent)
                }else {
                    Wrong("비밀번호가 틀립니다")
                }

            }else{
                Wrong("아이디가 틀립니다.")
            }

        }




        txtLoginSignup.setOnClickListener() {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
    private fun Wrong(message: String) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup, null)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = message

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("warning")
            .setPositiveButton("확인") {dialog, wich -> }
            //.setNeutralButton(){}
            //.setNegativeButton(){}
            .create()
        alertDialog.setView(view)
        alertDialog.show()
    }
    private fun confirm_ID(who: String, editID: String){
        database.child(who).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("failed")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(messageData in snapshot.getChildren()) {
                    id_list.add(messageData.key.toString())
                }
            }
        })
        for(i in id_list){
            if(editID.equals(i))
                id_confirm = true
        }
    }
    private fun confirm_PW(who: String, editPW: String){
        database.child(who).child(edtLoginID.text.toString()).child("user_pw")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    user_cpw = snapshot.getValue().toString()
                }
            })
        if(user_cpw.equals(editPW)) {
            pw_confirm = true
        }
    }
}

