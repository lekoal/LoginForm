package com.example.loginform.model

interface LoginViewContract {
    fun getLoginState(): LoginState
    fun checkLoginState()
}