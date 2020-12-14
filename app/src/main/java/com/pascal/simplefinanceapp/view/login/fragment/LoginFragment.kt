package com.pascal.simplefinanceapp.view.login.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.login.ResponseLogin
import com.pascal.simplefinanceapp.viewModel.ViewModelLogin
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: ViewModelLogin
    private lateinit var navController: NavController
    private lateinit var sharePref: SharedPreferences

    companion object {
        const val NAME = "LOGIN"
        const val LOGIN_SESSION = "login_session"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelLogin::class.java)

        sharePref = requireActivity().getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initView()
        attachObserve()
    }

    private fun initView() {
        btn_login.setOnClickListener {
            val email = login_email.text.toString()
            val password = login_password.text.toString()

            viewModel.loginDataView(email, password)
        }

        btn_Loginregister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        btn_language.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        if (sharePref.getInt(LOGIN_SESSION, 0) == 1) {
            navController.navigate(R.id.action_loginFragment_to_homeActivity)
        }
    }

    private fun attachObserve() {
        viewModel.responseLogin.observe(viewLifecycleOwner, Observer { showLogin(it)})
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoading(it) })
    }

    private fun showLogin(it: ResponseLogin?) {
        if (it?.isSuccess == true) {
            navController.navigate(R.id.action_loginFragment_to_homeActivity)
            sharePref.edit().putInt(LOGIN_SESSION, 1).commit()
            showToast("Login Completed")
        } else {
            showToast("Login Failed")
        }
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progress_login.visibility = View.VISIBLE
        } else {
            progress_login.visibility = View.GONE
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        attachObserve()
    }
}