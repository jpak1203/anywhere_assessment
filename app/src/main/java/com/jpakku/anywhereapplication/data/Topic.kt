package com.jpakku.anywhereapplication.data

import com.google.gson.annotations.SerializedName

data class Topic(
    @SerializedName("FirstURL") val firstUrl: String,
    @SerializedName("Icon") val icon: Icon,
    @SerializedName("Result") val result: String,
    @SerializedName("Text") val description: String
)
