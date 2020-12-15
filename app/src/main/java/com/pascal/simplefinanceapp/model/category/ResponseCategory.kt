package com.pascal.simplefinanceapp.model.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseCategory(

	@field:SerializedName("data")
	val data: List<DataCategory?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
) : Parcelable

@Parcelize
data class DataCategory(

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("category_id")
	val categoryId: String? = null
) : Parcelable
