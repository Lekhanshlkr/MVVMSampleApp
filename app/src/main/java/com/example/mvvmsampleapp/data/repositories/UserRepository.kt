package com.example.mvvmsampleapp.data.repositories

import android.os.AsyncTask
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.responses.ResponseParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db:AppDatabase
) {

    fun userLogin(email:String, password:String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        api.userLogin(email!!,password!!)
            .enqueue(object: Callback<ResponseBody>{

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }else{
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })
        return  loginResponse
    }

    fun saveUser(owner: LifecycleOwner,loginResponse:LiveData<String>):User? {
        val user = ResponseParser().stringTOJson(owner,loginResponse)
        if(user!=null){
            MyAsyncTask().execute(user)
        }
        return user
    }

    inner class MyAsyncTask:AsyncTask<User,User,User>(){
        override fun doInBackground(vararg params: User?): User {
            db.getUserDao().upsert(params[0]!!)

            return params[0]!!
        }

    }

    fun getLoggedUser() = db.getUserDao().getUser()


}