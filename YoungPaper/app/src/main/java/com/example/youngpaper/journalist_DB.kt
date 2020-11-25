package com.example.youngpaper

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class journalist_DB(
    var journalist_pw: String? = "",
    var journalist_name: String? = "",
    var journalist_email: String? ="",
    var journalist_phonenumber: String? ="",
    var journalist_birth: String? ="",
    var journalist_nickname: String? =""
)