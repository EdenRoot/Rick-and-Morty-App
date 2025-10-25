package dev.kiryao.rickandmorty.core.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)