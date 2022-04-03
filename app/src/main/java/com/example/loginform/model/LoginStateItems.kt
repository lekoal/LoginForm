package com.example.loginform.model

class LoginStateItems {
    private val successState = LoginState(isSuccess = true)
    private val errorStateWrongPassword = LoginState(isError = true, errorText = "Неверный пароль!")
    private val errorStateErrorLoading = LoginState(isError = true, errorText = "Ошибка загрузки")
    private val errorStateErrorNetwork = LoginState(isError = true, errorText = "Нет доступа к сети")
    private val loadingState = LoginState(isLoading = true)
}