package com.example.favmovies.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.favmovies.databinding.FragmentHomeBinding
import com.example.favmovies.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var moviesAdapter: MoviesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the LayoutManager that this RecyclerView will use.
        _binding?.moviesList?.layoutManager = GridLayoutManager(activity, 2)
        // Adapter class is initialized and list is passed in the param.
        moviesAdapter = MoviesAdapter(this@HomeFragment)
        // adapter instance is set to the recyclerview to inflate the items.
        _binding?.moviesList?.adapter = moviesAdapter


        homeViewModel.movies.observe(viewLifecycleOwner, { movies ->

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
        homeViewModel.getMovies()

        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun movieDetails(imdbId: String) {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }


        findNavController()
            .navigate(HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(imdbId))
    }
}