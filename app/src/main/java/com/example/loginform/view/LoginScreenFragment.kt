package com.example.loginform.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStateManagerControl
import com.example.loginform.databinding.FragmentLoginScreenBinding
import com.example.loginform.model.LoginViewContract
import com.example.loginform.presenter.LoginStatePresenter
import com.example.loginform.tools.ViewBindingFragment
import java.io.Serializable

const val PRESENTER_KEY = "PRESENTER_KEY"

class LoginScreenFragment :
    ViewBindingFragment<FragmentLoginScreenBinding>(FragmentLoginScreenBinding::inflate),
    LoginViewContract, Serializable {

    private var presenter = LoginStatePresenter()

    companion object {
        @JvmStatic
        fun newInstance() = LoginScreenFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            presenter = savedInstanceState.getSerializable(PRESENTER_KEY) as LoginStatePresenter
            presenter.onRestart(this)
        } else {
            presenter = LoginStatePresenter()
            presenter.onViewAttach(this)
        }

        binding.enterButton.setOnClickListener {
            presenter.onLogin(
                binding.loginInputEditText.text.toString(),
                binding.passwordInputEditText.text.toString()
            )
        }

        binding.registrationButton.setOnClickListener {
            Toast.makeText(requireContext(), "Нажата кнопка регистрации!", Toast.LENGTH_SHORT)
                .show()
        }

        binding.forgotPasswordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Нажата кнопка сброса пароля!", Toast.LENGTH_SHORT)
                .show()
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
            binding.progressBar.visibility = View.VISIBLE
            binding.progressLayout.visibility = View.VISIBLE
            blockScreen(true)
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun setSuccess(successText: String) {
        binding.successImage.visibility = View.VISIBLE
        binding.progressLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), successText, Toast.LENGTH_SHORT).show()
        onScreenTouchListener()
    }

    override fun setError(errorText: String) {
        binding.errorImage.visibility = View.VISIBLE
        binding.progressLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
        onScreenTouchListener()
    }

    private fun endProcess() {
        binding.successImage.visibility = View.GONE
        binding.errorImage.visibility = View.GONE
        binding.progressLayout.visibility = View.GONE
        blockScreen(false)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onScreenTouchListener() {
        binding.progressLayout.setOnTouchListener { _, _ ->
            endProcess()
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(PRESENTER_KEY, presenter.getCurrentPresenter())
        super.onSaveInstanceState(outState)
    }
}