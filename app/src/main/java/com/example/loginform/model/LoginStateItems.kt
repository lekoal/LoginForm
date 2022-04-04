package com.example.loginform.model

class LoginStateItems {
    private val successState = LoginState(
        isSuccess = true, successText = "Вход выполнен!"
    )
    private val errorStateWrongPassword = LoginState(
        isError = true, errorText = "Неверный логин или пароль!"
    )
    private val errorStateErrorLoading = LoginState(
        isError = true, errorText = "Ошибка загрузки"
    )
    private val errorStateErrorNetwork =
        LoginState(
            isError = true, errorText = "Нет доступа к сети"
        )

    fun getSuccess() = successState

    fun getWrongPassword() = errorStateWrongPassword

    fun getErrorLoading() = errorStateErrorLoading

    fun getErrorNetwork() = errorStateErrorNetwork

}