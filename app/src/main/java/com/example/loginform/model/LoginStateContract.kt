package com.example.loginform.model

import com.example.loginform.presenter.LoginStatePresenter

const val PASSWORD_ERROR = "PASSWORD_ERROR"
const val LOADING_ERROR = "LOADING_ERROR"
const val NETWORK_ERROR = "NETWORK_ERROR"

interface LoginStateContract {
    fun getSuccess(): LoginState
    fun getError(errorType: String): LoginState
    fun getErrorState(): LoginState
    fun onViewAttach(view: LoginViewContract)
    fun onLogin(userName: String, password: String)
    fun getCurrentPresenter(): LoginStatePresenter
    fun onRestart(view: LoginViewContract)
}