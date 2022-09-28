package com.dharmapal.jetpack_navigation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dharmapal.jetpack_navigation.databinding.FragmentLogInBinding
import com.dharmapal.jetpack_navigation.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)

        usernameFocusListener()
        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
        }

        binding.btnRegister.setOnClickListener {
                submitForm()
        }

        return binding.root
    }

    private fun submitForm() {
        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()
        binding.phoneContainer.helperText = validPhone()
        binding.usernameContainer.helperText = validUsername()

        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null
        val validPhone = binding.phoneContainer.helperText == null
        val validUsername = binding.usernameContainer.helperText == null

        if (validEmail && validPassword && validPhone && validUsername)
            resetForm()
        else
            invalidForm()
    }

    private fun resetForm() {
        findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
        Toast.makeText(context, "Register Successfully..!!", Toast.LENGTH_SHORT).show()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\n\nPassword: " + binding.passwordContainer.helperText
        if(binding.phoneContainer.helperText != null)
            message += "\n\nPhone: " + binding.phoneContainer.helperText

        //Toast.makeText(context, "Invalid Form", Toast.LENGTH_SHORT).show()
    }

    private fun phoneFocusListener() {
        binding.etPone1.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.phoneContainer.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = binding.etPone1.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }

    private fun passwordFocusListener() {

        binding.etPassword1.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.etPassword1.text.toString()
        if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }

    private fun emailFocusListener() {
        binding.etEmail1.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail1.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    private fun usernameFocusListener() {
        binding.etUsername1.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.usernameContainer.helperText = validUsername()
            }
        }
    }

    private fun validUsername(): String? {
        val username = binding.etUsername1.text.toString()
        if (username.isEmpty()){
            Toast.makeText(context,"Enter username", Toast.LENGTH_SHORT).show()
        }
        return null
    }
}