package ir.misterdeveloper.mypostsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.misterdeveloper.mypostsapplication.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
  lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFavoriteBinding.inflate(layoutInflater)


        return binding.root
    }


}