package com.jpakku.anywhereapplication.repository

import com.jpakku.anywhereapplication.data.CharacterItem
import com.jpakku.anywhereapplication.data.MyApi
import com.jpakku.anywhereapplication.data.RelatedTopics
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ListRepository @Inject constructor(private val myApi: MyApi) {

    fun getAllCharacters(): Single<List<CharacterItem>> {
        return myApi.getAllCharacters().flatMap {
            Single.just(it.toCharacterItemList())
        }
    }

    private fun RelatedTopics.toCharacterItemList() =
        topics.map {
            val name = it.description.split(" - ")[0]
            CharacterItem(
                name = name,
                description = it.description,
                icon = it.icon.url
            )
        }
}