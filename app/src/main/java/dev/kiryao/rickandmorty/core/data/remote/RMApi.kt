package dev.kiryao.rickandmorty.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RMApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): List<RMDto>

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}