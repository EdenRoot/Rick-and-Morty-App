package dev.kiryao.rickandmorty.core.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    val info: PageInfo,
    val results: List<RMDto>
)