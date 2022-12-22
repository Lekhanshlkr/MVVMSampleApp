package com.example.mvvmsampleapp.data.network


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyApi {

//    @FormUrlEncoded  // enable this when using POST method and change query to field
    @GET("login.php")
    fun userLogin(
        @Query("email")email:String,
        @Query("password")password:String
    ): Call<ResponseBody>

    companion object{
        operator fun invoke(): MyApi{
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/TwitterAndroidServer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}