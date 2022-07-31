package com.zubisofts.rickmortygadsapp.repo

import androidx.paging.PagingData
import com.zubisofts.rickmortygadsapp.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}