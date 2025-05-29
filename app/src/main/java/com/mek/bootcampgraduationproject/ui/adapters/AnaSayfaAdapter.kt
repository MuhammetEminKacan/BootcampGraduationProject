package com.mek.bootcampgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mek.bootcampgraduationproject.databinding.UrunlerCardTasarimBinding
import com.mek.bootcampgraduationproject.model.Yemekler

class AnaSayfaAdapter(private val mealsList : List<Yemekler>) : RecyclerView.Adapter<AnaSayfaAdapter.mealsViewHolder>() {

    inner class mealsViewHolder(val binding:UrunlerCardTasarimBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UrunlerCardTasarimBinding.inflate(layoutInflater, parent, false)
        return mealsViewHolder(binding)
    }

    override fun getItemCount(): Int = mealsList.size

    override fun onBindViewHolder(holder: mealsViewHolder, position: Int) {
        val yemek = mealsList[position]

        with(holder.binding){
            txtInformation.text = yemek.yemekAdi
            txtPrice.text = yemek.yemekFiyat

            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemekResimAdi}"
            Glide.with(holder.itemView).load(imageUrl).into(imgFood)
        }
    }

}