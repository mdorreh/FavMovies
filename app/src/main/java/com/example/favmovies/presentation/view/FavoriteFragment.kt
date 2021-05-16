package com.example.favmovies.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.favmovies.databinding.FragmentNotificationsBinding
import com.example.favmovies.presentation.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private  val favoriteViewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var moviesAdapter: MoviesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the LayoutManager that this RecyclerView will use.
        _binding?.moviesList?.layoutManager = GridLayoutManager(activity, 2)
        // Adapter class is initialized and list is passed in the param.
        moviesAdapter = MoviesAdapter(this@FavoriteFragment)
        // adapter instance is set to the recyclerview to inflate the items.
        _binding?.moviesList?.adapter = moviesAdapter


        favoriteViewModel.movies.observe(viewLifecycleOwner, { movies ->

            _binding?.loadingList?.visibility = View.GONE

            if (!movies.isNullOrEmpty()) {
                _binding?.moviesList?.visibility = View.VISIBLE
                _binding?.listError?.visibility = View.GONE
                moviesAdapter.moviesList(movies)
            } else {
                _binding?.moviesList?.visibility = View.GONE
                _binding?.listError?.visibility = View.VISIBLE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getMovies()

        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}