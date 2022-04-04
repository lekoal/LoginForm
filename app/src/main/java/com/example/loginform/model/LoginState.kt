package com.example.loginform.model

data class LoginState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorText: String = "",
    val successText: String = ""
)