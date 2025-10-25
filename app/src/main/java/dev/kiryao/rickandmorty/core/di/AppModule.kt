package dev.kiryao.rickandmorty.core.di

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.kiryao.rickandmorty.core.data.local.RMDatabase
import dev.kiryao.rickandmorty.core.data.local.RMEntity
import dev.kiryao.rickandmorty.core.data.paging.RMRemoteMediator
import dev.kiryao.rickandmorty.core.data.remote.RMApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRMDatabase(@ApplicationContext context: Context): RMDatabase {
        return Room.databaseBuilder(
            context,
            RMDatabase::class.java,
            "rickmorty.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRMApi(): RMApi {
        return Retrofit.Builder()
            .baseUrl(RMApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRMPager(rmDatabase: RMDatabase, rmApi: RMApi): Pager<Int, RMEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RMRemoteMediator(
                rmDatabase = rmDatabase,
                rmApi = rmApi
            ),
            pagingSourceFactory = {
                rmDatabase.dao.pagingSource()
            }
        )
    }
}