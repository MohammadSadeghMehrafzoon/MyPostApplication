package ir.misterdeveloper.mypostsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.misterdeveloper.mypostsapplication.adapter.PostsAdapter
import ir.misterdeveloper.mypostsapplication.databinding.FragmentHomeBinding
import ir.misterdeveloper.mypostsapplication.repository.AppRepository
import ir.misterdeveloper.mypostsapplication.util.Resource
import ir.misterdeveloper.mypostsapplication.util.errorSnack
import ir.misterdeveloper.mypostsapplication.viewModel.PostsViewModel
import ir.misterdeveloper.mypostsapplication.viewModel.ViewModelProviderFactory


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setViewModel()
        showPosts()

        return binding.root
    }


    private fun setViewModel() {

        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        postsViewModel = ViewModelProvider(this, factory)[PostsViewModel::class.java]

    }


    private fun showPosts() {


        postsViewModel.postsData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {

                    // goneShimmer()

                    response.data?.let { postResponse ->


                        val response = postResponse.toMutableList()


                        binding.recyclerViewPosts.adapter =
                            PostsAdapter(
                                requireActivity(),
                                postsResponse = response,

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
                        binding.constraint.errorSnack(message, Snackbar.LENGTH_LONG)
                        // Log.v("news", message)
                    }

                  ///  binding.swipeRefresh.isRefreshing = false

                }

                is Resource.Loading -> {

                    //visibleShimmer()


                }
            }
        })
    }


}