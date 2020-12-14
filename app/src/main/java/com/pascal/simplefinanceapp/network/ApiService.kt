package com.pascal.simplefinanceapp

import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.category.ResponseCategory
import com.pascal.simplefinanceapp.model.expence.ResponseExpence
import com.pascal.simplefinanceapp.model.income.ResponseIncome
import com.pascal.simplefinanceapp.model.login.ResponseLogin
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.*

interface ApiService {

    //Income
    //getData
    @GET("income/getData.php")
    fun getDataIncome(): Flowable<ResponseIncome>

    //insert
    @FormUrlEncoded
    @POST("income/insert.php")
    fun insertIncome(
        @Field("income_date") date: String,
        @Field("income_money") money: String,
        @Field("category_id") category: String
    ): Flowable<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("income/update.php")
    fun updateIncome(
        @Field("income_id") id: String,
        @Field("income_date") date: String,
        @Field("income_money") money: String,
        @Field("category_id") category: String
    ): Flowable<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("income/delete.php")
    fun deleteIncome(@Field("income_id") id: String): Flowable<ResponseAction>

    //getIncomeCategory
    @GET("income/getDatabyCategory.php?category_id=")
    fun getIncomebyCategory(
        @Query("category_id") id: String
    ): Flowable<ResponseIncome>

    @GET("income/getCategory.php")
    fun getCategoryIncome(): Flowable<ResponseCategory>


    //Expence
    //getData
    @GET("expence/getData.php")
    fun getDataExpence(): Flowable<ResponseExpence>

    //insert
    @FormUrlEncoded
    @POST("expence/insert.php")
    fun insertExpence(
        @Field("expence_date") date: String,
        @Field("expence_money") money: String,
        @Field("category_id") category: String
    ): Flowable<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("expence/update.php")
    fun updateExpence(
        @Field("expence_id") id: String,
        @Field("expence_date") date: String,
        @Field("expence_money") money: String,
        @Field("category_id") category: String
    ): Flowable<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("expence/delete.php")
    fun deleteExpence(@Field("expence_id") id: String): Flowable<ResponseAction>

    //getExpenceCategory
    @GET("expence/getDatabyCategory.php?category_id=")
    fun getExpencebyCategory(
        @Query("category_id") id: String
    ): Flowable<ResponseExpence>

    @GET("expence/getCategory.php")
    fun getCategoryExpence(): Flowable<ResponseCategory>


    //login
    @FormUrlEncoded
    @POST("login.php")
    fun loginData(
        @Field("user_email") email: String,
        @Field("user_password") password: String
    ): Flowable<ResponseLogin>

    //register
    @FormUrlEncoded
    @POST("register.php")
    fun registerData(
        @Field("user_email") email: String,
        @Field("user_password") password: String
    ): Flowable<ResponseAction>
}