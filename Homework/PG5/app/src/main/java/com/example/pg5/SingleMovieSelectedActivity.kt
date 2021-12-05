/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.pg5.fragments.FragmentSingleMovieSelected
import com.example.pg5.localdata.model.Movie
import com.example.pg5.viewmodels.ListMovieViewModel
import com.example.pg5.viewmodels.SingleMovieViewModel

private const val LOG = "SingleMovieSelectedActivity"

/**
 * This activity hosts my ViewPager2, I included my ViewPager2 adapter here as well
 */

class SingleMovieSelectedActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2

    private val singleMovieVM by viewModels<SingleMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie_selected)

        singleMovieVM.setMovieList(this)

        val movieID = intent.getIntExtra(MOVIE_ID, -1)

        viewPager = findViewById(R.id.single_movie_view_pager)

        val pagerAdapter = MoviePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        viewPager.setCurrentItem(movieID, false)

    }

    private inner class MoviePagerAdapter(
        fa: FragmentActivity
    ) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = singleMovieVM.getMovieNameList.size

        @SuppressLint("LongLogTag")
        override fun createFragment(position: Int): Fragment {
            Log.d(LOG, "Position in ViewPager2: $position")
            return FragmentSingleMovieSelected.newInstance(position)
        }
    }

    companion object {
        const val MOVIE_ID = "com.example.pg5.movie_id"
        fun newInstance(context: Context, movieID: Int): Intent {
            val intent = Intent(context, SingleMovieSelectedActivity::class.java).apply {
                putExtra(MOVIE_ID, movieID)
            }
            return intent
        }
    }
}