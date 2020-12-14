package com.pascal.simplefinanceapp.repo

import android.content.Context
import com.pascal.simplefinanceapp.ConfigNetwork
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.category.ResponseCategory
import com.pascal.simplefinanceapp.model.income.ResponseIncome
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryIncome(context: Context) {

    fun getData(responHandler: (ResponseIncome) -> Unit, errorHandler: (Throwable) -> Unit) {
        ConfigNetwork.service().getDataIncome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getDatabyCategory(
        id: String,
        responHandler: (ResponseIncome) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ConfigNetwork.service().getIncomebyCategory(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun hapusData(
        id: String,
        responHandler: (ResponseAction) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ConfigNetwork.service().deleteIncome(id ?: "").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun inputData(
        date: String, money: String, category: String,
        responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit
    ) {

        if (date.isNotEmpty() && money.isNotEmpty()) {
            ConfigNetwork.service().insertIncome(date, money, category)
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

    fun updateData(
        id: String, date: String, money: String, category: String,
        responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit
    ) {

        if (date.isNotEmpty() && money.isNotEmpty()) {

            ConfigNetwork.service().updateIncome(id, date, money, category)
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

    fun getCategory(responHandler: (ResponseCategory) -> Unit, errorHandler: (Throwable) -> Unit) {
        ConfigNetwork.service().getCategoryIncome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getResult(responHandler: (ResponseIncome) -> Unit, errorHandler: (Throwable) -> Unit) {

    }
}