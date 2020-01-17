package com.safwen.boxffficetest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safwen.boxffficetest.data.entities.SearchMovieResponse
import com.safwen.boxffficetest.data.remote.MovieDataSource
import io.reactivex.rxkotlin.subscribeBy

class MainActivityViewModel (private var movieDataSource: MovieDataSource): ViewModel()
{
    private var _movieResponse : MutableLiveData<SearchMovieResponse> = MutableLiveData()
    val movieResponse : LiveData<SearchMovieResponse> =_movieResponse
    fun fetchMovie(movieTitle: String?)
    {
        movieDataSource.fetchingMovie(movieTitle).subscribeBy (onComplete = {},onError = {},onNext = {
            _movieResponse.postValue(it)
        })
    }
}