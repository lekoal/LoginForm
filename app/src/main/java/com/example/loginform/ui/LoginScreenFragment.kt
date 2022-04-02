package com.example.loginform.ui

import com.example.loginform.databinding.FragmentLoginScreenBinding
import com.example.loginform.tools.ViewBindingFragment


class LoginScreenFragment : ViewBindingFragment<FragmentLoginScreenBinding>(FragmentLoginScreenBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = LoginScreenFragment()
    }
}