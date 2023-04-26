package com.jpakku.anywhereapplication.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpakku.anywhereapplication.data.CharacterItem
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(): ViewModel() {

    private val _characterDetail = MutableLiveData<CharacterItem>()
    val characterDetail: LiveData<CharacterItem>
        get() = _characterDetail

    fun setCharacterDetail(characterItem: CharacterItem) {
        _characterDetail.postValue(characterItem)
    }
}