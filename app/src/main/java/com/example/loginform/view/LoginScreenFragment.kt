package com.example.loginform.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.loginform.databinding.FragmentLoginScreenBinding
import com.example.loginform.model.LoginViewContract
import com.example.loginform.presenter.LoginStatePresenter
import com.example.loginform.tools.ViewBindingFragment


class LoginScreenFragment :
    ViewBindingFragment<FragmentLoginScreenBinding>(FragmentLoginScreenBinding::inflate),
    LoginViewContract {

    private var presenter = LoginStatePresenter()

    companion object {
        @JvmStatic
        fun newInstance() = LoginScreenFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewAttach(this)

        binding.enterButton.setOnClickListener {
            presenter.onLogin(binding.loginInputEditText.text.toString(), binding.passwordInputEditText.text.toString())
        }

        binding.registrationButton.setOnClickListener {
            Toast.makeText(requireContext(), "Нажата кнопка регистрации!", Toast.LENGTH_SHORT).show()
        }

        binding.forgotPasswordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Нажата кнопка сброса пароля!", Toast.LENGTH_SHORT).show()
        }
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

    override fun showLoading(isShown: Boolean) {
        if (isShown) {
            binding.progressLayout.visibility = View.VISIBLE
            blockScreen(true)
        } else {
            binding.progressBar.visibility = View.GONE
            blockScreen(false)
        }

    }

    override fun setSuccess(successText: String) {
        binding.successImage.visibility = View.VISIBLE
        Toast.makeText(requireContext(), successText, Toast.LENGTH_SHORT).show()
    }

    override fun setError(errorText: String) {
        binding.errorImage.visibility = View.VISIBLE
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
    }
}