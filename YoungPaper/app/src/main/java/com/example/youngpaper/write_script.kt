package com.example.youngpaper

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_write_script.*
import kotlinx.android.synthetic.main.activity_write_script.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Random

class write_script : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var mStorageRef: StorageReference
    var userid = ""
    var image : Uri? =null
    val random = Random()
    val currentDateTime = Calendar.getInstance().time
    var scriptId =""
    var scriptwriter=""
    var scriptcategory = ""
    var scripttitle = ""
    var scriptcontents = ""
    var scripttime = ""
    var scriptpicture=""
    var scriptviews= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_script)

        database = FirebaseDatabase.getInstance().reference
        if(intent.hasExtra("userid")){
            userid = intent.getStringExtra("userid")
            Toast.makeText(this, userid, Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            userid = "id"
        }

        script_image.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }

        commit.setOnClickListener{
            scriptId = random.nextInt(10000000).toString()
            ImageUpload()

            when (category_group.checkedRadioButtonId) {
                R.id.radioButton1 -> scriptcategory = "정치"
                R.id.radioButton2 ->scriptcategory = "경제"
                R.id.radioButton3 ->scriptcategory = "사회"
                R.id.radioButton4 ->scriptcategory = "취업"
            }

            scriptpicture = scriptId
            scriptviews = "0"
            scripttitle = script_title.text.toString()
            scriptcontents = script_content.text.toString()
            scripttime = SimpleDateFormat("yyyyMMddHHmmss",Locale.KOREA).format(currentDateTime)
            scriptwriter = script_writer.text.toString()
            //파이어베이스에 저장
            val script = script_DB(scriptwriter, scriptcategory, scripttitle, scriptcontents, scriptpicture, scripttime, scriptviews)
            database = FirebaseDatabase.getInstance().reference
            database.child("script").child(scriptId).setValue(script)
        }


    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                try {
                    image = data?.data
                    // 선택한 이미지에서 비트맵 생성
                    val `in` =
                        contentResolver.openInputStream(data!!.data!!)
                    var img = BitmapFactory.decodeStream(`in`)
                    `in`!!.close()
                    // 이미지 표시
                    script_image!!.setImageBitmap(img)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
    private fun ImageUpload(){
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        //var imgFileName = "IMAGE_" + timeStamp + "_.png"
        var imgFileName = scriptId
        mStorageRef = FirebaseStorage.getInstance().reference?.child("images")?.child(imgFileName)

        mStorageRef?.putFile(image!!)?.addOnSuccessListener {
            Toast.makeText(this, "image uploaded", Toast.LENGTH_SHORT).show()
        }

    }


}