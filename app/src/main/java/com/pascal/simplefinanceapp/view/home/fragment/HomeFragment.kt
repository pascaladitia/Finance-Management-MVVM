package com.pascal.simplefinanceapp.view.home.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.simplefinanceapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var navController: NavController

    companion object {
        const val NAME = "LOGIN"
        const val LOGIN_SESSION = "login_session"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        initView()
    }

    private fun initView() {
        home_logout.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1).apply {
                    setTitle(R.string.titleLogout)
                    setMessage(R.string.yakin)
                    setPositiveButton(R.string.yes) { dialogInterface, i ->

                        navController.navigate(R.id.action_homeFragment_to_loginActivity)
                        requireActivity().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
                            .putInt(LOGIN_SESSION, 0).commit()

                        dialogInterface.dismiss()
                    }
                    setNegativeButton(R.string.cancel) { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }
        }
    }
}