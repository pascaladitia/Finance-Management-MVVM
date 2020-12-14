package com.pascal.simplefinanceapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.category.ResponseCategory
import com.pascal.simplefinanceapp.model.expence.ResponseExpence
import com.pascal.simplefinanceapp.repo.RepositoryExpence

class ViewModelExpence(application: Application) : AndroidViewModel(application) {

    val repository = RepositoryExpence(application.applicationContext)

    var responseExpence = MutableLiveData<ResponseExpence>()
    var responseExpencebyCategory = MutableLiveData<ResponseExpence>()
    var responseCategory = MutableLiveData<ResponseCategory>()
    var responseDelete = MutableLiveData<ResponseAction>()
    var responseInput = MutableLiveData<ResponseAction>()
    var responseUpdate = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getExpenceView() {
        isLoading.value = true

        repository.getData({
            isLoading.value = false
            responseExpence.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun deleteExpenceView(id : String) {
        isLoading.value = true

        repository.hapusData(id, {
            isLoading.value = false
            responseDelete.value = it
            getExpenceView()
        } , {
            isLoading.value = false
            isError.value = it
        })
    }

    fun inputExpenceView(date: String, money: String, category: String) {
        repository.inputData(date, money, category, {
            responseInput.value = it
        }, {
            isError.value = it
        })
    }

    fun updateExpenceView(id: String, date: String, money: String, category: String) {
        repository.updateData(id, date, money, category, {
            responseUpdate.value = it
        }, {
            isError.value = it
        })
    }

    fun getExpenceByCategory(id: String) {
        isLoading.value = true

        repository.getDatabyCategory(id, {
            isLoading.value = false
            responseExpencebyCategory.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun getCategoryExpence() {
        isLoading.value = true

        repository.getCategory({
            isLoading.value = false
            responseCategory.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}