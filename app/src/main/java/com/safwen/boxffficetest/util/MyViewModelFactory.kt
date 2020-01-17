package com.safwen.boxffficetest.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.safwen.boxffficetest.data.remote.MovieDataSource

class MyViewModelFactory(val movieDataSource: MovieDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(movieDataSource::class.java).newInstance(movieDataSource) as T
    }

}