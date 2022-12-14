package com.dharmapal.jetpack_navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dharmapal.gatetouch_task.Retrofit.RetrofitService
import com.dharmapal.jetpack_navigation.MainViewmodel
import com.dharmapal.jetpack_navigation.MainViewmodelFactory
import com.dharmapal.jetpack_navigation.R
import com.dharmapal.jetpack_navigation.Repo
import com.dharmapal.jetpack_navigation.databinding.FragmentLogInBinding
import com.dharmapal.jetpack_navigation.databinding.FragmentRegisterBinding


class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var viewmodel: MainViewmodel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater)
        val viewModelFactory= MainViewmodelFactory(Repo(retrofitService))
        viewmodel= ViewModelProvider(this,viewModelFactory)[MainViewmodel::class.java]

        emailFocusListener()
        passwordFocusListener()
        binding.tvReg.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }

        binding.btnSubmit.setOnClickListener {
            submitForm()
        }

        return binding.root
    }

    private fun submitForm() {
        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()


        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null


        if (validEmail && validPassword )
            resetForm()
        else
            invalidForm()
    }

    private fun resetForm() {
        viewmodel.getUserdata(requireContext())

        Log.d("tagged",viewmodel.usernamelist.value.toString())
        viewmodel.usernamelist.observe(viewLifecycleOwner){
            var commonUser=false
            val filterlist=it.filter {
                it.email==binding.etEmail.text.toString() && it.password==binding.etPassword.text.toString()
            }
            if (filterlist.size!=0){
                commonUser=true
            }
            if (commonUser==true){
                findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                Toast.makeText(context, "Login Successfully..!!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Credentials are Invalid or Incorrect",Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun invalidForm() {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\n\nPassword: " + binding.passwordContainer.helperText

        //Toast.makeText(context, "Invalid Form", Toast.LENGTH_SHORT).show()
    }

    private fun passwordFocusListener() {

        binding.etPassword.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.etPassword.text.toString()
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
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

}