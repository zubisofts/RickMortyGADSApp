package com.zubisofts.rickmortygadsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zubisofts.rickmortygadsapp.data.model.Character
import com.zubisofts.rickmortygadsapp.repo.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val mutableCharacterData: MutableStateFlow<PagingData<Character>?> =
        MutableStateFlow(null)
    val characterData = mutableCharacterData.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            characterRepository.getCharacters().cachedIn(viewModelScope)
                .collect { characters ->
                    mutableCharacterData.value = characters
                }
        }
    }

}