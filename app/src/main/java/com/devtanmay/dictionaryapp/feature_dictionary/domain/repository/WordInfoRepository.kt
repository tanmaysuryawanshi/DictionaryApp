package com.devtanmay.dictionaryapp.feature_dictionary.domain.repository

import com.devtanmay.dictionaryapp.core.util.Resource
import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word:String): Flow<Resource<List<WordInfo>>>
}