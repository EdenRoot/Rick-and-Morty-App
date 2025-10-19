package dev.kiryao.rickandmorty.core.data.local

data class RMEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
