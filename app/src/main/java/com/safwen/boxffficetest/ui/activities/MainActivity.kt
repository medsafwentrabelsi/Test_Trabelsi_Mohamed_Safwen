package com.safwen.boxffficetest.ui.activities

import CircularProgressBarDialog
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.safwen.boxffficetest.Adapter.MovieDataAdapter
import com.safwen.boxffficetest.R
import com.safwen.boxffficetest.data.entities.MovieObject
import com.safwen.boxffficetest.data.remote.MovieDataSource
import com.safwen.boxffficetest.ui.DetailsActivity
import com.safwen.boxffficetest.util.MyViewModelFactory
import com.safwen.boxffficetest.viewModel.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import com.tooltip.Tooltip
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class MainActivity : AppCompatActivity(), MovieDataAdapter.OnItemClickListener {
    override fun onItemClicked(movie: MovieObject) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("imdbID", movie.imdbID)
        startActivity(intent)
    }

    private lateinit var circularProgressBarDialog: CircularProgressBarDialog

    private lateinit var viewModel: MainActivityViewModel
    private val movieAdapter = MovieDataAdapter(arrayListOf(), this)
    var tooltip: Tooltip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.safwen.boxffficetest.R.layout.activity_main)
        initView()
        initEvent()
    }

    private fun initView() {
        movie_list_main_activity.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter


        }
        circularProgressBarDialog = CircularProgressBarDialog(this)

         tooltip = Tooltip.Builder(search_view_main_activity)
            .setText(getString(com.safwen.boxffficetest.R.string.tooltipsearchmovie))
            .show()

    }

    private fun initEvent() {

//Instance View Model
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(MovieDataSource()))
            .get(MainActivityViewModel::class.java)
        //Observe Data From View Model
        viewModel.movieResponse.observe(this, Observer {
            if (it != null) {
                circularProgressBarDialog.hide()

                if (it.searchResult != null) {
                    no_data_found_image_view.visibility = View.GONE
                    movieAdapter.updateMovieList(it.searchResult)
                } else {
                    no_data_found_image_view.visibility = View.VISIBLE
                    movieAdapter.updateMovieList(emptyList())
                    Toast.makeText(

                        baseContext,
                        getString(com.safwen.boxffficetest.R.string.invalid_movie_name),
                        Toast.LENGTH_LONG
                    ).show()


                }

            }

        })

        //Call WS when user click search
        search_view_main_activity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @SuppressLint("CheckResult")
            override fun onQueryTextSubmit(query: String?): Boolean {

                circularProgressBarDialog.show()
                //Internet Test
                val isInternet = ReactiveNetwork.checkInternetConnectivity()
                isInternet.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(onSuccess = {
                        if (it) {
                            viewModel.fetchMovie(query)


                        } else {
                            Toast.makeText(
                                baseContext,
                                getString(com.safwen.boxffficetest.R.string.internet_not_found),
                                Toast.LENGTH_LONG
                            ).show()
                            circularProgressBarDialog.hide()
                        }
                    })

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tooltip?.dismiss()
                return false
            }
        })
    }
}
