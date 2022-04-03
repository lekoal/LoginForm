package com.example.loginform.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.loginform.databinding.FragmentLoginScreenBinding
import com.example.loginform.model.LoginState
import com.example.loginform.model.LoginViewContract
import com.example.loginform.presenter.LoginStatePresenter
import com.example.loginform.tools.ViewBindingFragment


class LoginScreenFragment :
    ViewBindingFragment<FragmentLoginScreenBinding>(FragmentLoginScreenBinding::inflate),
    LoginViewContract {

    companion object {
        @JvmStatic
        fun newInstance() = LoginScreenFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enterButton.setOnClickListener {
            checkLoginState()
        }

        binding.registrationButton.setOnClickListener {
            Toast.makeText(requireContext(), "Нажата кнопка регистрации!", Toast.LENGTH_SHORT).show()
        }

        binding.forgotPasswordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Нажата кнопка сброса пароля!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLoginState(): LoginState {
        val login = binding.loginInputEditText.text.toString()
        val password = binding.passwordInputEditText.text.toString()
        return if (login == "admin" && password == "admin") {
            LoginStatePresenter().getSuccess()
        } else {
            LoginStatePresenter().getErrorState()
        }
    }

    override fun checkLoginState() {
        val state = getLoginState()

        loadingTimer(state.isSuccess)
    }

    private fun blockScreen(block: Boolean) {
        if (block) {
            binding.enterButton.isEnabled = false
            binding.registrationButton.isEnabled = false
            binding.forgotPasswordButton.isEnabled = false
            binding.loginInputEditText.isEnabled = false
            binding.passwordInputEditText.isEnabled = false
        } else {
            binding.enterButton.isEnabled = true
            binding.registrationButton.isEnabled = true
            binding.forgotPasswordButton.isEnabled = true
            binding.loginInputEditText.isEnabled = true
            binding.passwordInputEditText.isEnabled = true
        }
    }

    private fun loadingTimer(isSuccess: Boolean) {
        if (isSuccess) {
            object : CountDownTimer(2000, 2000) {
                override fun onTick(p0: Long) {
                    binding.progressLayout.visibility = View.VISIBLE
                    blockScreen(true)
                }

                override fun onFinish() {
                    val success = LoginStatePresenter().getSuccess()
                    binding.progressBar.visibility = View.GONE
                    binding.successImage.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), success.successText, Toast.LENGTH_SHORT).show()
                }

            }.start()
        } else {
            object : CountDownTimer(2000, 2000) {
                override fun onTick(p0: Long) {
                    binding.progressLayout.visibility = View.VISIBLE
                    blockScreen(true)
                }

                override fun onFinish() {
                    val error = LoginStatePresenter().getErrorState()
                    binding.progressBar.visibility = View.GONE
                    binding.errorImage.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), error.errorText, Toast.LENGTH_SHORT).show()
                }

            }.start()
        }
    }
}