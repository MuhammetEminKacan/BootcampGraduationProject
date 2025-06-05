package com.mek.bootcampgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mek.bootcampgraduationproject.databinding.UrunCardTasarimBinding
import com.mek.bootcampgraduationproject.databinding.UrunlerCardTasarimBinding
import com.mek.bootcampgraduationproject.model.Yemekler

class FavorilerAdapter(private val favoriteMealsList : List<Yemekler>,private val onDeleteClick: (Yemekler) -> Unit) : RecyclerView.Adapter<FavorilerAdapter.favoriteMealsViewHolder>() {

    inner class favoriteMealsViewHolder(val binding : UrunCardTasarimBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriteMealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UrunCardTasarimBinding.inflate(layoutInflater,parent,false)
        return  favoriteMealsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteMealsList.size
    }

    override fun onBindViewHolder(holder: favoriteMealsViewHolder, position: Int) {
        val favYemek = favoriteMealsList[position]
        with(holder.binding){
            val imgUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${favYemek.yemekResimAdi}"
            Glide.with(holder.itemView).load(imgUrl).into(imgFoodCard)
            txtFoodName.text = favYemek.yemekAdi
            txtPrice.text = favYemek.yemekFiyat
            txtCount.visibility = View.GONE
            txtCountInfo.visibility = View.GONE
            txtTotalPrice.visibility = View.GONE

            imgClose.setOnClickListener {
                onDeleteClick(favYemek)
            }

        }
    }

}