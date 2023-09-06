package com.devtanmay.dictionaryapp.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devtanmay.dictionaryapp.feature_dictionary.data.local.Entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class WordInfoDatabase:RoomDatabase() {
    abstract val dao: WordInfoDoa
}