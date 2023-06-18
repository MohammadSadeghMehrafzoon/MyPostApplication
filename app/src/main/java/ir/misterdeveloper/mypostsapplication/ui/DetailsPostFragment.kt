package ir.misterdeveloper.mypostsapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import ir.misterdeveloper.mypostsapplication.adapter.CommentsAdapter
import ir.misterdeveloper.mypostsapplication.databinding.FragmentDetailsPostBinding
import ir.misterdeveloper.mypostsapplication.repository.AppRepository
import ir.misterdeveloper.mypostsapplication.util.Resource
import ir.misterdeveloper.mypostsapplication.util.errorSnack
import ir.misterdeveloper.mypostsapplication.viewModel.CommentsViewModel
import ir.misterdeveloper.mypostsapplication.viewModel.ViewModelProviderFactory

class DetailsPostFragment : Fragment() {

    private lateinit var binding: FragmentDetailsPostBinding
    private lateinit var commentsViewModel: CommentsViewModel
    private val shimmerFrameLayout: ShimmerFrameLayout? = null

    private var postId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsPostBinding.inflate(layoutInflater)

        setViewModel()
        showComments()


        return binding.root
    }

    private fun setViewModel() {

        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        commentsViewModel = ViewModelProvider(this, factory)[CommentsViewModel::class.java]

    }


    private fun showComments() {

        postId = requireArguments().getInt("postId")

        commentsViewModel.getComments(postId!!)
        commentsViewModel.commentsData.observe(viewLifecycleOwner, Observer { it ->

            it.getContentIfNotHandled()?.let {
                when (it) {
                    is Resource.Success -> {

                        goneShimmer()
                        it.data?.let { commentsResponse ->

                            val response = commentsResponse.toMutableList()


                            binding.recyclerViewComments.adapter =
                                CommentsAdapter(
                                    commentsResponse = response)

                            binding.recyclerViewComments.layoutManager = LinearLayoutManager(
                                requireActivity(),
                                RecyclerView.VERTICAL,
                                false
                            )

                        }
                    }
                    is Resource.Error -> {
                        it.message?.let { message ->

                            visibleShimmer()
                            binding.constraint.errorSnack(message, Snackbar.LENGTH_LONG)

                        }
                    }

                    is Resource.Loading -> {
                        visibleShimmer()
                    }
                }


            }

        })
    }

    private fun visibleShimmer() {

        shimmerFrameLayout?.stopShimmer()
        binding.shimmerComments.visibility = View.VISIBLE

    }

    private fun goneShimmer() {

        shimmerFrameLayout?.stopShimmer()
        binding.shimmerComments.visibility = View.GONE

    }


}