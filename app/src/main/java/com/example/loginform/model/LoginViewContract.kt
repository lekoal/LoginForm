package com.example.loginform.model

interface LoginViewContract {
    fun showLoading(isShown: Boolean)
    fun setSuccess(successText: String)
    fun setError(errorText: String)
}