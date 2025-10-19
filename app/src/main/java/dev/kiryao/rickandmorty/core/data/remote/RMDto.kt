package dev.kiryao.rickandmorty.core.data.remote

data class RMDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)