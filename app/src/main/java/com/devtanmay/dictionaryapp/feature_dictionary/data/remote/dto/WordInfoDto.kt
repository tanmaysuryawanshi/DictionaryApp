package com.devtanmay.dictionaryapp.feature_dictionary.data.remote.dto

import com.devtanmay.dictionaryapp.feature_dictionary.data.local.Entity.WordInfoEntity
import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,

    val word: String
){
    fun toWordInfoEntity():WordInfoEntity{
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },

      phonetic = phonetic,
            word = word
        )
    }


}