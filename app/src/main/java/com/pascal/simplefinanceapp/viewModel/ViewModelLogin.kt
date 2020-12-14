package com.pascal.simplefinanceapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.login.ResponseLogin
import com.pascal.simplefinanceapp.repo.RepositoryLogin

class ViewModelLogin(application: Application) : AndroidViewModel(application) {

    val repository = RepositoryLogin(application.applicationContext)

    var responseLogin = MutableLiveData<ResponseLogin>()
    var responseRegister = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun loginDataView(email: String, password: String) {
        isLoading.value = true

        repository.loginData(email, password, {
            isLoading.value = false
            responseLogin.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun registerDataView(email: String, password: String) {
        repository.registerData(email, password, {
            responseRegister.value = it
        }, {
            isError.value = it
        })
    }
}