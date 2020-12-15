package com.pascal.simplefinanceapp

import com.pascal.simplefinanceapp.utils.Constan
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ConfigNetwork {

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constan.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    fun service(): ApiService = getRetrofit().create(ApiService::class.java)
}