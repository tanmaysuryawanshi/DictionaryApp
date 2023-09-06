package com.devtanmay.dictionaryapp.feature_dictionary.data.remote.dto

import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.Meaning

data class MeaningDto(

    var definitions: List<DefinitionDto>,
    val partOfSpeech: String
){
    fun toMeaning():Meaning{

        return Meaning(
            definitions = definitions.map{it.toDefinition()},
            partOfSpeech = partOfSpeech
        )

    }
}