package com.mek.bootcampgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mek.bootcampgraduationproject.R
import com.mek.bootcampgraduationproject.databinding.FragmentUrunDetayBinding
import com.mek.bootcampgraduationproject.model.Yemekler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrunDetayFragment : Fragment() {
    private var _binding : FragmentUrunDetayBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUrunDetayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle : UrunDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        val foodThumb = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemekResimAdi}"
        Glide.with(requireView()).load(foodThumb).into(binding.imgFood)
        binding.txtFoodDetail.text = gelenYemek.yemekAdi
        binding.txtFoodPrice.text = "$ ${gelenYemek.yemekFiyat}"
        binding.txtFoodName.text = gelenYemek.yemekAdi

        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        adetArttirma(gelenYemek)

    }

    private fun adetArttirma(yemekInfo : Yemekler) {
        var count = 0
        var totalPrice = 0
        binding.imgPlus.setOnClickListener {
            count++
            binding.txtCount.text = count.toString()
            totalPrice = count * yemekInfo.yemekFiyat!!.toInt()
            binding.txtTotalPrice.text = totalPrice.toString()
        }

        binding.imgMinus.setOnClickListener {
            if (count <= 0){
                binding.txtCount.text = "0"
                binding.txtTotalPrice.text = "0"
            }else{
                count--
                binding.txtCount.text = count.toString()
                totalPrice = count * yemekInfo.yemekFiyat!!.toInt()
                binding.txtTotalPrice.text = totalPrice.toString()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}