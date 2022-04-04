package com.example.loginform.presenter

import android.os.CountDownTimer
import android.view.View
import com.example.loginform.model.*
import java.io.Serializable

class LoginStatePresenter : LoginStateContract, Serializable {
    private var view: LoginViewContract? = null
    private var isSuccess = false
    private var errorText = ""

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

    override fun onViewAttach(view: LoginViewContract) {
        this.view = view
    }

    override fun onLogin(userName: String, password: String) {
        view?.showLoading(true)
        if (userName == "admin" && password == "admin") {
            isSuccess = true
            object : CountDownTimer(2000, 2000) {
                override fun onTick(p0: Long) {}

                override fun onFinish() {
                    view?.showLoading(false)
                    view?.setSuccess(getSuccess().successText)
                }

            }.start()
        } else {
            isSuccess = false
            object : CountDownTimer(2000, 2000) {
                override fun onTick(p0: Long) {}

                override fun onFinish() {
                    view?.showLoading(false)
                    errorText = getErrorState().errorText
                    view?.setError(errorText)
                }

            }.start()
        }
    }

    override fun getCurrentPresenter(): LoginStatePresenter {
        return this
    }

    override fun onRestart(view: LoginViewContract) {
        this.view = view
        if (isSuccess) {
            view.setSuccess(getSuccess().successText)
        } else {
            view.setError(errorText)
        }
    }
}