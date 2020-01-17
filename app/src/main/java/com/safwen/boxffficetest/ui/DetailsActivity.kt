package com.safwen.boxffficetest.ui

import CircularProgressBarDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.safwen.boxffficetest.R
import com.safwen.boxffficetest.data.remote.MovieDataSource
import com.safwen.boxffficetest.util.MyViewModelFactory
import com.safwen.boxffficetest.viewModel.DetailsActivityViewModel

import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailsActivityViewModel
    private lateinit var circularProgressBarDialog: CircularProgressBarDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initView()
        initEvent()


    }

    private fun initView() {
        circularProgressBarDialog = CircularProgressBarDialog(this)
        circularProgressBarDialog.show()
    }
    private fun initEvent() {
        val intent = intent
        var imdbid = intent.getStringExtra("imdbID")
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(MovieDataSource()))
            .get(DetailsActivityViewModel::class.java)
        viewModel.fetchMovie(imdbid)
        viewModel.movieDetails.observe(this, Observer {
            if (it != null) {
                circularProgressBarDialog.hide()
                movie_title_text_view_activity_details.text = it.movieTitle
                release_date_text_view_activity_details.text=it.releasedDate
                Glide.with(this)
                    .load(it.moviePoster)
                    .into(movie_paster_image_view_activity_details)
                if(it.rating!!.size>0)
                critics_rating_bar_activity_details.rating= it.rating!![0].value.substringBefore("/").toFloat()


            }

        })

    }
}
