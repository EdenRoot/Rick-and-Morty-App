package dev.kiryao.rickandmorty.core.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RMDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)