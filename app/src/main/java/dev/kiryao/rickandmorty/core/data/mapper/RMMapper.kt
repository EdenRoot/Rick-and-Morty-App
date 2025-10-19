package dev.kiryao.rickandmorty.core.data.mapper

import dev.kiryao.rickandmorty.core.data.local.RMEntity
import dev.kiryao.rickandmorty.core.data.remote.RMDto
import dev.kiryao.rickandmorty.core.domain.model.RMItem
import kotlin.Int

fun RMDto.toRMEntity(): RMEntity {
    return RMEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}

fun RMEntity.toRMItem(): RMItem {
    return RMItem(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}