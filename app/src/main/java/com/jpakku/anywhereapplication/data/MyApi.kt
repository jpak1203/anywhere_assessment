package com.jpakku.anywhereapplication.data

import com.jpakku.anywhereapplication.BuildConfig
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MyApi {

    @GET(BuildConfig.BASE_QUERY)
    fun getAllCharacters(): Single<RelatedTopics>

}