package com.mek.bootcampgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mek.bootcampgraduationproject.R
import com.mek.bootcampgraduationproject.databinding.FragmentAnaSayfaBinding
import com.mek.bootcampgraduationproject.ui.adapters.AnaSayfaAdapter
import com.mek.bootcampgraduationproject.ui.viewmodels.AnaSayfaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() {
    private  var _binding : FragmentAnaSayfaBinding ?= null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AnaSayfaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnaSayfaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavoriteMeals()

        viewModel.observeMealsLiveData().observe(viewLifecycleOwner) { yemekListesi ->
            viewModel.observeFavoriler().observe(viewLifecycleOwner) { favoriYemekler ->
                val favoriIdSet = favoriYemekler.mapNotNull { it.yemekId.toIntOrNull() }.toSet()

                val anaSayfaAdapter = AnaSayfaAdapter(
                    yemekListesi,
                    favoriIdSet,
                    onItemClick = { secilenYemek ->
                        val action = AnaSayfaFragmentDirections
                            .actionAnaSayfaFragmentToUrunDetayFragment(secilenYemek)
                        findNavController().navigate(action)
                    },
                    onFavoriteClick = { favoriYemek, isNowFavorited ->
                        if (isNowFavorited) {
                            viewModel.addMealToFavorites(favoriYemek)
                        } else {
                            viewModel.deleteMealFromFavorites(favoriYemek)
                        }


                        viewModel.getAllFavoriteMeals()
                    }
                )

                binding.recyclerViewUrunler.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = anaSayfaAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}