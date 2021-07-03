package com.chibuzo.tiler.adapter

import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityMyCatalogBinding
import com.chibuzo.tiler.databinding.RecyclerMyCatalogBinding
import com.chibuzo.tiler.datastore.DatabaseHandler
import com.chibuzo.tiler.model.MyCatalogTilesModel
import java.io.File

class MyCatalogAdapter(private val myCatalogTiles: ArrayList<MyCatalogTilesModel>,
                       private val parentBinding: ActivityMyCatalogBinding) : RecyclerView.Adapter<MyCatalogAdapter.MyViewHolder>() {
    private var gottenPosition: Int = -1

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

        val contextWrapper = ContextWrapper(holder.itemView.context)
        val directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, myCatalogTiles[position].tileImageName)

        Glide.with(holder.itemView.context)
            .load(file)
            .transform(FitCenter(), RoundedCorners(11))
            .into(holder.binding.myCatalogTileImage)

        holder.binding.myCatalogTileName.text = myCatalogTiles[position].tileName

        if (myCatalogTiles[position].availability) {
            holder.binding.availabilityIndicator.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.messengerChatmateStatus))
        } else {
            holder.binding.availabilityIndicator.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.danger))
        }

        parentBinding.myCatalogMenuLayout.genericMenuLayout.setOnClickListener {
            parentBinding.myCatalogMenuLayout.genericMenuLayout.visibility = View.GONE
        }

        parentBinding.myCatalogMenuLayout.tileDeleteMenu.genericMenuLayout.setOnClickListener {
            parentBinding.myCatalogMenuLayout.genericMenuLayout.visibility = View.GONE
            val databaseHandler = DatabaseHandler(holder.itemView.context)
            databaseHandler.deleteCatalogTiles(myCatalogTiles[gottenPosition])
            myCatalogTiles.removeAt(gottenPosition)
            notifyDataSetChanged()
        }

        holder.binding.myCatalogTileLayout.setOnLongClickListener {
            parentBinding.myCatalogMenuLayout.genericMenuLayout.visibility = View.VISIBLE
            gottenPosition = position
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return myCatalogTiles.size
    }

    class MyViewHolder(val binding: RecyclerMyCatalogBinding) :
        RecyclerView.ViewHolder(binding.root)
}


