package dev.kiryao.rickandmorty.core.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RMDao {

    @Upsert
    suspend fun upsertAll(characters: List<RMEntity>)

    @Query("SELECT * FROM rmEntity")
    fun pagingSource(): PagingSource<Int, RMEntity>

    @Query("DELETE FROM rmEntity")
    suspend fun clearAll()

}