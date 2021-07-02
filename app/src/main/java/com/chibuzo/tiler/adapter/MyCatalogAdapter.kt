package com.chibuzo.tiler.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.databinding.RecyclerMyCatalogBinding
import com.chibuzo.tiler.model.MyCatalogTilesModel


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

        val decodedString: ByteArray = Base64.decode(myCatalogTiles[position].tileImageString, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        Glide.with(holder.itemView.context)
            .load(bitmap)
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


