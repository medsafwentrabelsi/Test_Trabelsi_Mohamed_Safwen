package com.safwen.boxffficetest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safwen.boxffficetest.data.entities.MovieObject
import com.safwen.boxffficetest.data.remote.MovieDataSource
import io.reactivex.rxkotlin.subscribeBy

class DetailsActivityViewModel  (private var movieDataSource: MovieDataSource): ViewModel()
{
    private var _movieDetails: MutableLiveData<MovieObject> = MutableLiveData()
    val movieDetails : LiveData<MovieObject> =_movieDetails
    fun fetchMovie(movieImd: String?)
    {
        movieDataSource.fetchingMovieDetails(movieImd).subscribeBy (onComplete = {},onError = {},onNext = {
            _movieDetails.postValue(it)
        })
    }
}