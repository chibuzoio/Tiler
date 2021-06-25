package com.chibuzo.tiler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.RecyclerNigerianTilesBinding
import com.chibuzo.tiler.model.NigerianTilesModel

class NigerianTilesAdapter(val nigerianTiles: ArrayList<NigerianTilesModel>) :
    RecyclerView.Adapter<NigerianTilesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NigerianTilesAdapter.MyViewHolder {
        val binding = RecyclerNigerianTilesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NigerianTilesAdapter.MyViewHolder, position: Int) {
//        holder.binding.nigerianTilesImage.drawable
    }

    override fun getItemCount(): Int {
        return nigerianTiles.size
    }

    class MyViewHolder(val binding: RecyclerNigerianTilesBinding) :
        RecyclerView.ViewHolder(binding.root)
}


