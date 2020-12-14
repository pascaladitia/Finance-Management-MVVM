package com.pascal.simplefinanceapp.model.income

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseIncome(

	@field:SerializedName("data")
	val data: List<DataIncome?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
) : Parcelable

@Parcelize
data class DataIncome(

	@field:SerializedName("income_money")
	val incomeMoney: String? = null,

	@field:SerializedName("income_id")
	val incomeId: String? = null,

	@field:SerializedName("category_id")
	val categoryId: String? = null,

	@field:SerializedName("income_date")
	val incomeDate: String? = null
) : Parcelable
