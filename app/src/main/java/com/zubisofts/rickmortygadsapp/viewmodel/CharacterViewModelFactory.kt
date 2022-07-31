package com.zubisofts.rickmortygadsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zubisofts.rickmortygadsapp.repo.CharacterRepository

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory(private val characterRepository: CharacterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharacterViewModel::class.java)){
            return CharacterViewModel(characterRepository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}