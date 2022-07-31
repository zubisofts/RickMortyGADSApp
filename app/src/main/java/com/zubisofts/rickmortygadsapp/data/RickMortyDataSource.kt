package com.zubisofts.rickmortygadsapp.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zubisofts.rickmortygadsapp.data.model.Character
import com.zubisofts.rickmortygadsapp.data.model.RickMortyResponse
import com.zubisofts.rickmortygadsapp.network.RickMortyApiService
import com.zubisofts.rickmortygadsapp.util.STARTING_PAGE

class RickMortyDataSource(private val apiService: RickMortyApiService) :
    PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = if (params.key == null) STARTING_PAGE else params.key
        return try {
            val  response: RickMortyResponse = apiService.getCharacters()
            val next = Uri.parse(response.info.next).getQueryParameter("page")?.toInt()
            val characters = response.results
            LoadResult.Page(
                data = characters,
                prevKey = if (position == STARTING_PAGE) null else position!! - 1,
                nextKey = if (position == response.info.pages) null else position + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}