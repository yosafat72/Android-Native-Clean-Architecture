package com.example.clean.data.model

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<UserDetailEntity>
)
