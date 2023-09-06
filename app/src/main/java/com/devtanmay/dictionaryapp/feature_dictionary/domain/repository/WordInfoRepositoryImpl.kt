package com.devtanmay.dictionaryapp.feature_dictionary.domain.repository

import com.devtanmay.dictionaryapp.core.util.Resource
import com.devtanmay.dictionaryapp.feature_dictionary.data.local.WordInfoDoa
import com.devtanmay.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class WordInfoRepositoryImpl (
    private val api:DictionaryApi,
    private val dao: WordInfoDoa
        ):WordInfoRepository{

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> =flow{

     emit(Resource.Loading())
    val wordInfos=dao.getWordInfos(word).map{it.toWordInfo()}
     emit(Resource.Loading(wordInfos))
try {
val remoteWordInfos=api.getWordInfo(word)
    dao.deleteWordInfos(remoteWordInfos.map { it.word })
    dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })

}catch (e:HttpException){

    emit(Resource.Error("Something went wrong",
    data = wordInfos))
}
        catch (e:IOException){
            emit(Resource.Error("Couldn't reach Server",
                data = wordInfos))
        }

        val newWordInfos=dao.getWordInfos(word).map { it.toWordInfo() }

        emit(Resource.Success(newWordInfos))
    }

}