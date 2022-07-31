package com.zubisofts.rickmortygadsapp.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zubisofts.rickmortygadsapp.data.RickMortyDataSource
import com.zubisofts.rickmortygadsapp.data.model.Character
import com.zubisofts.rickmortygadsapp.network.RickMortyApiService
import com.zubisofts.rickmortygadsapp.util.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val apiService: RickMortyApiService) : CharacterRepository {
    private val pagingConfig = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
    )
    override fun getCharacters(): Flow<PagingData<Character>> {
        return  Pager(
            config = pagingConfig,
            pagingSourceFactory = { RickMortyDataSource(apiService) }
        ).flow
    }
}