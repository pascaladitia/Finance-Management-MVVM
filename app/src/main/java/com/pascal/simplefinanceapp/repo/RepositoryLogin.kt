package com.pascal.simplefinanceapp.repo

import android.content.Context
import com.pascal.simplefinanceapp.ConfigNetwork
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.login.ResponseLogin
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryLogin(context: Context) {

    fun loginData(email: String, password: String,
                  responHandler: (ResponseLogin) -> Unit, errorHandler: (Throwable) -> Unit) {

        ConfigNetwork.service().loginData(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun registerData(
        email: String, password: String,
        responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit
    ) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            ConfigNetwork.service().registerData(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responHandler(it)
                }, {
                    errorHandler(it)
                })
        } else {
            errorHandler
        }
    }
}