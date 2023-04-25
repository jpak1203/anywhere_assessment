package com.jpakku.anywhereapplication.ui.list

import androidx.lifecycle.ViewModel
import com.jpakku.anywhereapplication.data.CharacterItem
import com.jpakku.anywhereapplication.repository.ListRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(private val listRepository: ListRepository): ViewModel() {

    fun getAllCharacters(): Single<List<CharacterItem>> {
        return listRepository.getAllCharacters()
    }

}