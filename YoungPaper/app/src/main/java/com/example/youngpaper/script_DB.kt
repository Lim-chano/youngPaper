package com.example.youngpaper

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class script_DB(
    var script_writer: String="",
    var script_category: String? = "",
    var script_title: String? = "",
    var script_contents: String? = "",
    var script_picture: String? = "",
    var script_time: String? = "",
    var script_views: String? = ""
)