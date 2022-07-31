package com.zubisofts.rickmortygadsapp.network

import com.zubisofts.rickmortygadsapp.data.model.Character
import com.zubisofts.rickmortygadsapp.data.model.RickMortyResponse
import com.zubisofts.rickmortygadsapp.util.API_CONNECT_TIMEOUT
import com.zubisofts.rickmortygadsapp.util.API_READ_TIMEOUT
import com.zubisofts.rickmortygadsapp.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


interface RickMortyApiService  {
    @GET("character")
    suspend fun getCharacters(): RickMortyResponse
}

fun createApiService(): RickMortyApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createLoggingInterceptor())
        .build()
        .create(RickMortyApiService::class.java)
}

private fun createLoggingInterceptor(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}