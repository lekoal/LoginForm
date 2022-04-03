package com.example.loginform.view

import android.os.Bundle
import android.view.View
import com.example.loginform.databinding.FragmentLoginScreenBinding
import com.example.loginform.tools.ViewBindingFragment


class LoginScreenFragment : ViewBindingFragment<FragmentLoginScreenBinding>(FragmentLoginScreenBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = LoginScreenFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}