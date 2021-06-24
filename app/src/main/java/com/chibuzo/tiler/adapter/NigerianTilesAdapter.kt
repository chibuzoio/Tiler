package com.chibuzo.tiler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chibuzo.tiler.R
import com.chibuzo.tiler.model.NigerianTilesModel

class NigerianTilesAdapter(val nigerianTiles: ArrayList<NigerianTilesModel>) :
    RecyclerView.Adapter<NigerianTilesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NigerianTilesAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_nigerian_tiles, parent, false)
        return MyViewHolder(view)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: NigerianTilesAdapter.MyViewHolder, position: Int) {

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return nigerianTiles.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}


