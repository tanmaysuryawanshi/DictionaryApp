package com.devtanmay.dictionaryapp.core.util

import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.WordInfo

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T?= null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}