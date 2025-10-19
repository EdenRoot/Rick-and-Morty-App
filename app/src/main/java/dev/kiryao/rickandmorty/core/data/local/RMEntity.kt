package dev.kiryao.rickandmorty.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RMEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
