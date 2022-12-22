package com.example.mvvmsampleapp.data.network.responses


import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entities.User
import org.json.JSONArray
import org.json.JSONObject

class ResponseParser {


    fun stringTOJson(owner: LifecycleOwner, response:LiveData<String>):User?{
        response.observe(owner) {
            val json =  JSONObject(it)
            val msg = json.getString("msg")
            if(msg == "pass login"){
                val userInfo = JSONArray(json.getString("info"))
                val userCredentials = userInfo.getJSONObject(0)
                val user_id = userCredentials.getString("USER_ID")
                val fulll_name = userCredentials.getString("FULLL_NAME")
                val email = userCredentials.getString("EMAIL")
                val password = userCredentials.getString("PASSWORD")
                val picture_path = userCredentials.getString("PICTURE_PATH")
                user  = User(user_id,fulll_name,email,password,picture_path)
            }else{
                user = User()
            }
        }
        return user
    }
    companion object{
        var user:User?=null
    }

}