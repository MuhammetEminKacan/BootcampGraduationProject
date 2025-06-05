package com.mek.bootcampgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mek.bootcampgraduationproject.R
import com.mek.bootcampgraduationproject.databinding.FragmentAlisverisSepetBinding
import com.mek.bootcampgraduationproject.ui.viewmodels.AlisverisSepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlisverisSepetFragment : Fragment() {

    private var _binding : FragmentAlisverisSepetBinding ?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AlisverisSepetViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlisverisSepetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMeals("muhammet_emin")
        viewModel.observeMealsLiveData().observe(viewLifecycleOwner){sepet ->
            for (yemek in sepet){
                if (yemek.yemekAdi !=null){
                    Log.e("sepet","${yemek.yemekAdi}")
                }else{
                    Log.e("sepet","null geliyor")
                }

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}