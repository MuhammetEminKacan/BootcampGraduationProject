package com.mek.bootcampgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mek.bootcampgraduationproject.R
import com.mek.bootcampgraduationproject.databinding.FragmentAlisverisSepetBinding
import com.mek.bootcampgraduationproject.model.SepetYemek
import com.mek.bootcampgraduationproject.ui.adapters.SepetAdapter
import com.mek.bootcampgraduationproject.ui.viewmodels.AlisverisSepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlisverisSepetFragment : Fragment() {

    private var _binding : FragmentAlisverisSepetBinding ?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AlisverisSepetViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlisverisSepetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMeals("emin_seyfi")
        viewModel.observeMealsLiveData().observe(viewLifecycleOwner){sepet ->
            val gruplanmisListe = sepet
                .groupBy { it.yemekAdi }
                .map { (_, yemekGrubu) ->
                    val ilkYemek = yemekGrubu.first()
                    val toplamAdet = yemekGrubu.sumOf { it.yemekSiparisAdet }

                    ilkYemek.copy(yemekSiparisAdet = toplamAdet)
                }

            val sepetAdapter = SepetAdapter(gruplanmisListe){ secilenYemek ->
                viewModel.deleteMealFromCards(secilenYemek.sepetYemekId,"emin_seyfi")
            }

            binding.rvSepet.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = sepetAdapter
            }

            val toplamTutar = gruplanmisListe.sumOf { it.yemekFiyat * it.yemekSiparisAdet }
            binding.totalSepetTutar.text = "$ ${toplamTutar}"
        }








    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}