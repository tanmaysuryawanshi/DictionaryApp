package com.devtanmay.dictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.devtanmay.dictionaryapp.feature_dictionary.data.Util.GsonParser
import com.devtanmay.dictionaryapp.feature_dictionary.data.local.Converters
import com.devtanmay.dictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import com.devtanmay.dictionaryapp.feature_dictionary.data.local.WordInfoDoa
import com.devtanmay.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.devtanmay.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import com.devtanmay.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepositoryImpl
import com.devtanmay.dictionaryapp.feature_dictionary.domain.usecases.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWorldInfoUseCase(repository:WordInfoRepository):GetWordInfo{
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db:WordInfoDatabase,
        api:DictionaryApi
    ):WordInfoRepository{
        return WordInfoRepositoryImpl(api,db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app:Application): WordInfoDatabase
    {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,"word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaryApi():DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}