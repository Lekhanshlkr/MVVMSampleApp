package com.example.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.db.UserDao
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.data.network.responses.ResponseParser
import com.example.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel(
    private val repository: UserRepository
):ViewModel() {

    var email:String?=null
    var password:String?=null

    var authListener:AuthListener?=null
    var owner:LifecycleOwner?=null

    fun attach(owner: LifecycleOwner){
        this.owner=owner
    }

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        val loginResponse = repository.userLogin(email!!,password!!)
        authListener?.onSuccess(repository.saveUser(owner!!,loginResponse))
    }

    fun loggedUser() = repository.getLoggedUser()
}