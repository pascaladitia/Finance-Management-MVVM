package com.pascal.simplefinanceapp.repo

import android.content.Context
import com.pascal.simplefinanceapp.ConfigNetwork
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.category.ResponseCategory
import com.pascal.simplefinanceapp.model.expence.ResponseExpence
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryExpence(context: Context) {

    fun getData(responHandler: (ResponseExpence) -> Unit, errorHandler: (Throwable) -> Unit) {
        ConfigNetwork.service().getDataExpence()
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
        responHandler: (ResponseExpence) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ConfigNetwork.service().getExpencebyCategory(id)
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
        ConfigNetwork.service().deleteExpence(id ?: "").subscribeOn(Schedulers.io())
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
            ConfigNetwork.service().insertExpence(date, money, category)
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

            ConfigNetwork.service().updateExpence(id, date, money, category)
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
        ConfigNetwork.service().getCategoryExpence()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }
}