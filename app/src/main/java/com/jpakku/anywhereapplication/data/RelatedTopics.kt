package com.jpakku.anywhereapplication.data

import com.google.gson.annotations.SerializedName

data class RelatedTopics(
    @SerializedName("RelatedTopics") val topics: List<Topic>
)