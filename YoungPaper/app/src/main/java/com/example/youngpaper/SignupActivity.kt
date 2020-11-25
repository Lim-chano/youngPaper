package com.example.youngpaper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.alert_popup.*

class SignupActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        // 회원가입 누르면 연결
        var who: String = ""
        database = FirebaseDatabase.getInstance().reference
        var id_list: ArrayList<String>  = ArrayList()
        id_list.clear()
        database.child("user").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("failed")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(messageData in snapshot.getChildren()) {
                    id_list.add(messageData.key.toString())
                }

            }
        })
        database.child("writer").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("failed")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(messageData in snapshot.getChildren()) {
                    id_list.add(messageData.key.toString())
                }
                println(id_list)
                println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            }
        })

        signupButton.setOnClickListener {
            var id_confirm = true
            for (i in id_list) {
                if (signupID.text.toString().equals(i))
                    id_confirm = false
            }
            if (id_confirm) {
                if (signupPW.text.toString().equals(signupconfirmPW.text.toString())) {

                    when (radio_group_signup.checkedRadioButtonId) {
                        R.id.user_radioButton_signup -> who = "user"
                        R.id.writer_radioButton_signup -> who = "writer"
                    }
                    NewUser(
                        who,
                        signupID.text.toString(),
                        signupName.text.toString(),
                        signupBirth.text.toString(),
                        signupEmail.text.toString(),
                        signupPhonenumber.text.toString(),
                        signupPW.text.toString()
                    )
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    passworderrorpopup()
                }
            } else {
                IDerrorpopup()
                signupID.text.clear()
            }

        }

}
    private fun passworderrorpopup() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup, null)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = "비밀번호가 일치하지 않습니다"
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("warning")
            .setPositiveButton("확인") {dialog, wich -> }
            //.setNeutralButton(){}
            //.setNegativeButton(){}
            .create()
        alertDialog.setView(view)
        alertDialog.show()
    }
    private fun IDerrorpopup() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup, null)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = "중복된 아이디가 있습니다."

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("warning")
            .setPositiveButton("확인") {dialog, wich -> }
            //.setNeutralButton(){}
            //.setNegativeButton(){}
            .create()
        alertDialog.setView(view)
        alertDialog.show()
    }
    private fun NewUser(who: String, userId: String, userName: String, userBirth: String, userEmail: String, userPhonenum: String, userPw: String) {
        val user = User_DB(userName, userBirth, userEmail, userPhonenum, userPw)

        database = FirebaseDatabase.getInstance().reference
        database.child(who).child(userId).setValue(user)

    }
}
