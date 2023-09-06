package com.devtanmay.dictionaryapp.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devtanmay.dictionaryapp.feature_dictionary.data.local.Entity.WordInfoEntity

@Dao
interface WordInfoDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos:List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfos(words:List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word ||'%'")
    suspend fun getWordInfos(word:String):List<WordInfoEntity>

}