package com.mek.bootcampgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mek.bootcampgraduationproject.R
import com.mek.bootcampgraduationproject.databinding.UrunlerCardTasarimBinding
import com.mek.bootcampgraduationproject.model.Yemekler

class AnaSayfaAdapter(private val mealsList : List<Yemekler>,
                      private val favoritedSet: Set<Int>,
                      private val onItemClick: (Yemekler) -> Unit,
                      private val onFavoriteClick: (Yemekler,Boolean) -> Unit
                      ) : RecyclerView.Adapter<AnaSayfaAdapter.mealsViewHolder>() {

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

        with(holder.binding){
            if (favoritedSet.contains(yemek.yemekId?.toInt())) {
                imgFav.setImageResource(R.drawable.ic_favorite_full)
            } else {
                imgFav.setImageResource(R.drawable.ic_favorite)
            }

            imgFav.setOnClickListener {
                yemek.yemekId?.let {
                    val isNowFavorited = !favoritedSet.contains(it.toInt())


                    imgFav.setImageResource(
                        if (isNowFavorited) R.drawable.ic_favorite_full
                        else R.drawable.ic_favorite
                    )


                    onFavoriteClick(yemek, isNowFavorited)
                }
            }
        }

        with(holder.binding){
            imgFood.setOnClickListener {
                onItemClick(yemek)
            }
        }
    }

}