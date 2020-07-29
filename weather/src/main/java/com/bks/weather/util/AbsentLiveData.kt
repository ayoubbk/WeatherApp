package com.bks.weather.util

import androidx.lifecycle.LiveData

class AbsentLiveData <T : Any?> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T> createLiveData (): LiveData<T> {
            return AbsentLiveData()
        }
    }
}