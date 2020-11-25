package com.example.youngpaper

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User_DB(
    var user_name: String? = "",
    var user_birth: String? ="",
    var user_email: String? ="",
    var user_phonenumber: String? ="",
    var user_pw: String? = "",
    var user_interest: String? ="",
    var user_nickname: String? =""

)