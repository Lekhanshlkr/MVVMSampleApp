package com.example.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID="0"

@Entity
data class User(
    var user_id:String?=null,
    var fulll_name:String?=null,
    var email:String?=null,
    var password:String?=null,
    var picture_path:String?=null
) {
    @PrimaryKey(autoGenerate = false)
    var uid:String = CURRENT_USER_ID
}