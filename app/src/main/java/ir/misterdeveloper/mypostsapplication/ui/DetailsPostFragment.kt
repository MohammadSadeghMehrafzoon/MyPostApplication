package ir.misterdeveloper.mypostsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.misterdeveloper.mypostsapplication.databinding.FragmentDetailsPostBinding

class DetailsPostFragment : Fragment() {

    lateinit var binding: FragmentDetailsPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentDetailsPostBinding.inflate(layoutInflater)


        return binding.root
    }


}