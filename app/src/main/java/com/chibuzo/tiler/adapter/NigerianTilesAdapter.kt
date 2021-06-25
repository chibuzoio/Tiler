package com.chibuzo.tiler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chibuzo.tiler.databinding.RecyclerNigerianTilesBinding
import com.chibuzo.tiler.model.NigerianTilesModel

class NigerianTilesAdapter(private val nigerianTiles: ArrayList<NigerianTilesModel>) :
    RecyclerView.Adapter<NigerianTilesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerNigerianTilesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.nigerianTilesDimension.genericHeader.text = "Tile Dimension"
        holder.binding.nigerianTilesPackingSize.genericHeader.text = "Packing Size Per Carton"
        holder.binding.nigerianTilesSquareMeter.genericHeader.text = "Square Meter Per Carton"

        Glide.with(holder.itemView).load(nigerianTiles[position].imageName)
            .into(holder.binding.nigerianTilesImage)

        holder.binding.nigerianTilesSquareMeter.genericBody.text = nigerianTiles[position].squareMeter
        holder.binding.nigerianTilesPackingSize.genericBody.text = nigerianTiles[position].packingSize
        holder.binding.nigerianTilesDimension.genericBody.text = nigerianTiles[position].dimension
    }

    override fun getItemCount(): Int {
        return nigerianTiles.size
    }

    class MyViewHolder(val binding: RecyclerNigerianTilesBinding) :
        RecyclerView.ViewHolder(binding.root)
}


