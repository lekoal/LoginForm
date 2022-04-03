package com.example.loginform.presenter

import com.example.loginform.model.*

class LoginStatePresenter(val isLoading: Boolean = false) : LoginStateContract {

    override fun getSuccess() = LoginStateItems().getSuccess()

    override fun getError(errorType: String): LoginState {
        return when(errorType) {
            PASSWORD_ERROR -> LoginStateItems().getWrongPassword()
            LOADING_ERROR -> LoginStateItems().getErrorLoading()
            else -> LoginStateItems().getErrorNetwork()
        }
    }

    override fun getErrorState(): LoginState {
        return when((0..9).random()) {
            8 -> getError(LOADING_ERROR)
            9 -> getError(NETWORK_ERROR)
            else -> getError(PASSWORD_ERROR)
        }
    }
}