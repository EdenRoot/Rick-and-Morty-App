package dev.kiryao.rickandmorty.core.domain.model

data class RMItem(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)