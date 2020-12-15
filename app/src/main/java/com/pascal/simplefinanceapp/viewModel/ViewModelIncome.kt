package com.pascal.simplefinanceapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.category.ResponseCategory
import com.pascal.simplefinanceapp.model.income.ResponseIncome
import com.pascal.simplefinanceapp.repo.RepositoryIncome

class ViewModelIncome(application: Application) : AndroidViewModel(application) {

    val repository = RepositoryIncome(application.applicationContext)

    var responseIncome = MutableLiveData<ResponseIncome>()
    var responseCategory = MutableLiveData<ResponseCategory>()
    var responseIncomebyCategory = MutableLiveData<ResponseIncome>()
    var responseDelete = MutableLiveData<ResponseAction>()
    var responseInput = MutableLiveData<ResponseAction>()
    var responseUpdate = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getIncomeView() {
        isLoading.value = true

        repository.getData({
            isLoading.value = false
            responseIncome.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun deleteIncomeView(id : String) {
        isLoading.value = true

        repository.hapusData(id, {
            isLoading.value = false
            responseDelete.value = it
            getIncomeView()
        } , {
            isLoading.value = false
            isError.value = it
        })
    }

    fun inputIncomeView(date: String, money: String, category: String) {
        repository.inputData(date, money, category, {
            responseInput.value = it
        }, {
            isError.value = it
        })
    }

    fun updateIncomeView(id: String, date: String, money: String, category: String) {
        repository.updateData(id, date, money, category, {
            responseUpdate.value = it
        }, {
            isError.value = it
        })
    }

    fun getIncomeByCategory(id: String) {
        isLoading.value = true

        repository.getDatabyCategory(id, {
            isLoading.value = false
            responseIncomebyCategory.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun getCategoryIncome() {
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