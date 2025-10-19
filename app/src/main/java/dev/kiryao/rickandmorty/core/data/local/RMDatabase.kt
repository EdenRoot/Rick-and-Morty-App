package dev.kiryao.rickandmorty.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RMEntity::class], version = 1)
abstract class RMDatabase: RoomDatabase() {

    abstract val dao: RMDao
}