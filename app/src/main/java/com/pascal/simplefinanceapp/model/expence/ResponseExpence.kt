package com.pascal.simplefinanceapp.model.expence

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseExpence(

	@field:SerializedName("data")
	val data: List<DataExpence?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
) : Parcelable

@Parcelize
data class DataExpence(

	@field:SerializedName("category_id")
	val categoryId: String? = null,

	@field:SerializedName("expence_money")
	val expenceMoney: String? = null,

	@field:SerializedName("expence_id")
	val expenceId: String? = null,

	@field:SerializedName("expence_date")
	val expenceDate: String? = null
) : Parcelable
