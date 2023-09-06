package com.devtanmay.dictionaryapp.feature_dictionary.domain.usecases

import com.devtanmay.dictionaryapp.core.util.Resource
import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.devtanmay.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetWordInfo(private val repository: WordInfoRepository) {

    operator fun invoke(word:String):
            Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()){
           return flow<Resource<List<WordInfo>>> {  }
        }
        return repository.getWordInfo(word)
    }

}