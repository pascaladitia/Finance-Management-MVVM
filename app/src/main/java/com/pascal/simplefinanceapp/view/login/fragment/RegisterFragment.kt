package com.pascal.simplefinanceapp.view.login.fragment

import android.os.Bundle
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
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.viewModel.ViewModelLogin
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private lateinit var viewModel: ViewModelLogin
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelLogin::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initBtn()
        attachObserve()
    }

    private fun initBtn() {
        btn_registerCancel.setOnClickListener {
            activity?.onBackPressed()
        }

        btn_register.setOnClickListener {
            val email = register_email.text.toString()
            val password = register_password.text.toString()

            viewModel.registerDataView(email, password)
        }
    }

    private fun attachObserve() {
        viewModel.responseRegister.observe(viewLifecycleOwner, Observer { showLogin(it) })
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showLogin(it: ResponseAction?) {
        if (it?.isSuccess == true) {
            activity?.onBackPressed()
            showToast("Register Completed")
        } else {
            showToast("Register Failed")
        }
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}