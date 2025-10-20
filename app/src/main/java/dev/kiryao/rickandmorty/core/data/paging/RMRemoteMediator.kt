package dev.kiryao.rickandmorty.core.data.paging

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.kiryao.rickandmorty.core.data.local.RMDatabase
import dev.kiryao.rickandmorty.core.data.local.RMEntity
import dev.kiryao.rickandmorty.core.data.mapper.toRMEntity
import dev.kiryao.rickandmorty.core.data.remote.RMApi
import retrofit2.HttpException
import java.io.IOException

class RMRemoteMediator(
    private val rmDatabase: RMDatabase,
    private val rmApi: RMApi
): RemoteMediator<Int, RMEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RMEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val characters = rmApi.getCharacters(
                page = loadKey
            )

            rmDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    rmDatabase.dao.clearAll()
                }
                val rmEntity = characters.map { it.toRMEntity() }
                rmDatabase.dao.upsertAll(rmEntity)
            }

            MediatorResult.Success(endOfPaginationReached = characters.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}