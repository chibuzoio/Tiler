package com.chibuzo.tiler.adapter

import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.databinding.RecyclerMyCatalogBinding
import com.chibuzo.tiler.model.MyCatalogTilesModel
import java.io.File

class MyCatalogAdapter(private val myCatalogTiles: ArrayList<MyCatalogTilesModel>) :
    RecyclerView.Adapter<MyCatalogAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerMyCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if ((itemCount - position) < 2) {
            val params = holder.binding.myCatalogTileLayout.layoutParams as ViewGroup.MarginLayoutParams
            params.bottomMargin = 250
            holder.binding.myCatalogTileLayout.layoutParams = params
        }

        Log.e("arrayContents", "arrayContents here are $myCatalogTiles")

        val contextWrapper = ContextWrapper(holder.itemView.context)
        val directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, myCatalogTiles[position].tileImageName)

        Glide.with(holder.itemView.context)
            .load(file)
            .transform(FitCenter(), RoundedCorners(11))
            .into(holder.binding.myCatalogTileImage)

        holder.binding.myCatalogTileName.text = myCatalogTiles[position].tileName
    }

    override fun getItemCount(): Int {
        return myCatalogTiles.size
    }

    class MyViewHolder(val binding: RecyclerMyCatalogBinding) :
        RecyclerView.ViewHolder(binding.root)
}


