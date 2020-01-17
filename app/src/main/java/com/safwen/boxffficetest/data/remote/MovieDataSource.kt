package com.safwen.boxffficetest.data.remote

import com.safwen.boxffficetest.data.entities.MovieObject
import com.safwen.boxffficetest.data.entities.SearchMovieResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieDataSource {
    val boxOfficeApi by lazy {
        BoxOfficeApi.create()
    }
    fun fetchingMovie(movieTitle: String?): Observable<SearchMovieResponse> {

        return Observable.create { emitter ->
            boxOfficeApi.getSearchedMovie(movieTitle = movieTitle,apiKey = "c870ccf0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {


                        if (it != null) {
                            emitter.onNext(it)
                        }
                    },
                    onError = { it.toString() },
                    onComplete = { println("Done!") }


                )
        }
    }
    fun fetchingMovieDetails(movieImd: String?): Observable<MovieObject> {

        return Observable.create { emitter ->
            boxOfficeApi.getMovieDetails(movieImd = movieImd,apiKey = "c870ccf0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {


                        if (it != null) {
                            emitter.onNext(it)
                        }
                    },
                    onError = { it.toString() },
                    onComplete = { println("Done!") }


                )
        }
    }
}