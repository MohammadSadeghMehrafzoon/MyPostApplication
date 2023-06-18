package ir.misterdeveloper.mypostsapplication.ui

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import ir.misterdeveloper.mypostsapplication.R
import ir.misterdeveloper.mypostsapplication.adapter.PostsAdapter
import ir.misterdeveloper.mypostsapplication.database.AppDatabase

import ir.misterdeveloper.mypostsapplication.databinding.FragmentHomeBinding
import ir.misterdeveloper.mypostsapplication.repository.AppRepository
import ir.misterdeveloper.mypostsapplication.util.Resource
import ir.misterdeveloper.mypostsapplication.util.errorSnack
import ir.misterdeveloper.mypostsapplication.viewModel.PostsViewModel
import ir.misterdeveloper.mypostsapplication.viewModel.ViewModelProviderFactory






class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var postsViewModel: PostsViewModel
    private val shimmerFrameLayout: ShimmerFrameLayout? = null
    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        appDatabase = AppDatabase.getInstance(requireContext())

        setViewModel()
        showPosts()

        binding.includeToolbar.imageViewShowBookMark.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }

        return binding.root
    }


    private fun setViewModel() {

        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        postsViewModel = ViewModelProvider(this, factory)[PostsViewModel::class.java]

    }


    private fun showPosts() {


        postsViewModel.postsData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {

                    goneShimmer()

                    response.data?.let { postResponse ->


                        val responseData = postResponse.toMutableList()

                        binding.recyclerViewPosts.adapter =
                            PostsAdapter(
                                requireActivity(),
                                postsResponse = responseData,
                                appDatabase
                            )
                        binding.recyclerViewPosts.layoutManager = LinearLayoutManager(
                            requireActivity(),
                            RecyclerView.VERTICAL,
                            false
                        )


                    }
                }

                is Resource.Error -> {

                    response.message?.let { message ->
                        visibleShimmer()
                        binding.constraint.errorSnack(message, Snackbar.LENGTH_LONG)

                    }


                }

                is Resource.Loading -> {

                    visibleShimmer()


                }
            }
        })
    }


    private fun visibleShimmer() {

        shimmerFrameLayout?.stopShimmer()
        binding.shimmerPosts.visibility = View.VISIBLE

    }

    private fun goneShimmer() {

        shimmerFrameLayout?.stopShimmer()
        binding.shimmerPosts.visibility = View.GONE

    }





}