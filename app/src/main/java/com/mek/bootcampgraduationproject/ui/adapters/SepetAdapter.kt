package com.mek.bootcampgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mek.bootcampgraduationproject.databinding.UrunCardTasarimBinding
import com.mek.bootcampgraduationproject.model.SepetYemek

class SepetAdapter(private val sepetList : List<SepetYemek>,private val onDeleteClick: (SepetYemek) -> Unit) : RecyclerView.Adapter<SepetAdapter.sepetCardTasarımViewHolder>() {

    inner class sepetCardTasarımViewHolder(val binding : UrunCardTasarimBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sepetCardTasarımViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UrunCardTasarimBinding.inflate(layoutInflater,parent,false)
        return sepetCardTasarımViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  sepetList.size
    }

    override fun onBindViewHolder(holder: sepetCardTasarımViewHolder, position: Int) {
        val gelenYemek = sepetList[position]

        with(holder.binding){
            txtFoodName.text = gelenYemek.yemekAdi
            txtPrice.text = "$ ${gelenYemek.yemekFiyat}"
            txtCount.text = gelenYemek.yemekSiparisAdet.toString()
            txtTotalPrice.text = "$ ${gelenYemek.yemekFiyat * gelenYemek.yemekSiparisAdet}"

            val resimLink = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemekResimAdi}"

            Glide.with(holder.itemView).load(resimLink).into(imgFoodCard)

            imgClose.setOnClickListener {
                onDeleteClick(gelenYemek)
                Toast.makeText(holder.itemView.context, "yemek sepetten silindi", Toast.LENGTH_SHORT).show()
            }

        }


    }

}