package com.tyagiabhinav.loremipsum.model.dao

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultState<out R> {

    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()
//    object Loading : ResultState<Nothing>()
}
