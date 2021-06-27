package com.chibuzo.tiler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chibuzo.tiler.databinding.RecyclerMyCatalogBinding
import com.chibuzo.tiler.model.MyCatalogModel

class MyCatalogAdapter(private val myCatalogTiles: ArrayList<MyCatalogModel>) :
    RecyclerView.Adapter<MyCatalogAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerMyCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return myCatalogTiles.size
    }

    class MyViewHolder(val binding: RecyclerMyCatalogBinding) :
        RecyclerView.ViewHolder(binding.root)
}


