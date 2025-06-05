package com.mek.bootcampgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mek.bootcampgraduationproject.R
import com.mek.bootcampgraduationproject.databinding.FragmentFavorilerBinding
import com.mek.bootcampgraduationproject.ui.adapters.FavorilerAdapter
import com.mek.bootcampgraduationproject.ui.viewmodels.FavorilerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorilerFragment : Fragment() {
    private var _binding : FragmentFavorilerBinding ?= null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavorilerViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavorilerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavoriteMeals()

        viewModel.observeFavoriler().observe(viewLifecycleOwner){ yemekler ->
            val favorilerAdapter = FavorilerAdapter(
                favoriteMealsList = yemekler,
                onDeleteClick = { silinecekYemek ->
                    viewModel.deleteMealFromFavorites(silinecekYemek)
                    Toast.makeText(requireContext(), "seçtiğiniz yemek favorilerden silindi", Toast.LENGTH_SHORT).show()
                    viewModel.getAllFavoriteMeals()
                })

            binding.rvFavorites.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapter = favorilerAdapter
            }

            binding.rvFavorites
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}