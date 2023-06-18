package ir.misterdeveloper.mypostsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.misterdeveloper.mypostsapplication.adapter.FavoriteAdapter
import ir.misterdeveloper.mypostsapplication.database.AppDatabase
import ir.misterdeveloper.mypostsapplication.database.Favorite
import ir.misterdeveloper.mypostsapplication.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(requireContext())

        showBookMark()

        return binding.root
    }


    private fun showBookMark() {


        val favorite: ArrayList<Favorite>? = appDatabase.daoDatabase().favoritePost as ArrayList<Favorite>?

        binding.recyclerViewItemBookMark.adapter =
            FavoriteAdapter(
                requireActivity(),
                favoritePost = favorite,
                appDatabase
            )
        binding.recyclerViewItemBookMark.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )


    }


}