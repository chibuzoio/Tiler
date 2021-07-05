package com.chibuzo.tiler.adapter

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chibuzo.tiler.R
import com.chibuzo.tiler.activity.GenericTileDisplayActivity
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

        holder.binding.myCatalogTileLayout.setOnClickListener {
            var intent = Intent(holder.itemView.context, GenericTileDisplayActivity::class.java)
            intent.putExtra("catalogTileId", myCatalogTiles[position].catalogTileId.toString())
            intent.putExtra("tileName", myCatalogTiles[position].tileName)
            intent.putExtra("dimension", myCatalogTiles[position].dimension)
            intent.putExtra("tileSquareMeter", myCatalogTiles[position].tileSquareMeter.toString())
            intent.putExtra("packingSize", myCatalogTiles[position].packingSize.toString())
            intent.putExtra("marketPrice", myCatalogTiles[position].marketPrice.toString())
            intent.putExtra("sellingPrice", myCatalogTiles[position].sellingPrice.toString())
            intent.putExtra("warehouseName", myCatalogTiles[position].warehouseName)
            intent.putExtra("phoneNumber", myCatalogTiles[position].phoneNumber)
            intent.putExtra("originCountry", myCatalogTiles[position].originCountry)
            intent.putExtra("availability", myCatalogTiles[position].availability.toString())
            intent.putExtra("tileImageName", myCatalogTiles[position].tileImageName)
            holder.itemView.context.startActivity(intent)
        }

        parentBinding.myCatalogMenuLayout.genericMenuLayout.setOnClickListener {
            parentBinding.myCatalogMenuLayout.genericMenuLayout.visibility = View.GONE
        }

        parentBinding.updateCustomTile.genericButtonLayout.setOnClickListener {
            val tileName = parentBinding.tileNameInput.genericTextInputEditor.text
            val xDirectionDimen = parentBinding.xDirectionInputEditor.text
            val yDirectionDimen = parentBinding.yDirectionInputEditor.text
            val squareMeter = parentBinding.squareMeterInput.genericNumberDecimalInputEditor.text
            val packingSize = parentBinding.packingSizeInput.genericNumberDecimalInputEditor.text
            val marketPrice = parentBinding.marketPriceInput.genericNumberDecimalInputEditor.text
            val sellingPrice = parentBinding.sellingPriceInput.genericNumberDecimalInputEditor.text
            val warehouseName = parentBinding.warehouseNameInput.genericTextInputEditor.text
            val phoneNumber = parentBinding.phoneNumberInput.genericNumberInputEditor.text
            val originCountry = parentBinding.originCountryInput.genericTextInputEditor.text

            if (tileName.isNotBlank() && xDirectionDimen.isNotBlank()
                && yDirectionDimen.isNotBlank() && squareMeter.isNotBlank()
                && packingSize.isNotBlank() && marketPrice.isNotBlank()
                && sellingPrice.isNotBlank() && warehouseName.isNotBlank()
                && phoneNumber.isNotBlank() && originCountry.isNotBlank()) {
                myCatalogTiles[gottenPosition].tileName = tileName.toString().trim()
                myCatalogTiles[gottenPosition].dimension = "${xDirectionDimen.trim()} X ${yDirectionDimen.trim()}"
                myCatalogTiles[gottenPosition].tileSquareMeter = squareMeter.toString().trim().toFloat()
                myCatalogTiles[gottenPosition].packingSize = packingSize.toString().trim().toInt()
                myCatalogTiles[gottenPosition].marketPrice = marketPrice.toString().trim().toDouble()
                myCatalogTiles[gottenPosition].sellingPrice = sellingPrice.toString().trim().toDouble()
                myCatalogTiles[gottenPosition].warehouseName = warehouseName.toString().trim()
                myCatalogTiles[gottenPosition].phoneNumber = phoneNumber.toString().trim()
                myCatalogTiles[gottenPosition].originCountry = originCountry.toString().trim()

                val databaseHandler = DatabaseHandler(holder.itemView.context)
                databaseHandler.updateCatalogTiles(myCatalogTiles[gottenPosition])

                parentBinding.tileCatalogEditorLayout.visibility = View.GONE

                notifyDataSetChanged()
            }
        }

        parentBinding.myCatalogMenuLayout.tileEditMenu.genericMenuLayout.setOnClickListener {
            val contextWrapper = ContextWrapper(holder.itemView.context)
            val directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE)
            val file = File(directory, myCatalogTiles[gottenPosition].tileImageName)

            Glide.with(holder.itemView.context)
                .load(file)
                .transform(FitCenter(), RoundedCorners(11))
                .into(parentBinding.tileUpdateImage)

            parentBinding.tileNameInput.genericTextInputEditor.setText(myCatalogTiles[gottenPosition].tileName)

            val dimensionString = myCatalogTiles[gottenPosition].dimension
            val xIndex = dimensionString.indexOf("X")
            val xDirectionDimension = dimensionString.substring(0, xIndex).trim()
            val yDirectionDimension = dimensionString.substring(xIndex + 1).trim()

            parentBinding.myCatalogMenuLayout.genericMenuLayout.visibility = View.GONE
            parentBinding.tileCatalogEditorLayout.visibility = View.VISIBLE

            parentBinding.xDirectionInputEditor.setText(xDirectionDimension)
            parentBinding.yDirectionInputEditor.setText(yDirectionDimension)
            parentBinding.squareMeterInput.genericNumberDecimalInputEditor.setText(myCatalogTiles[gottenPosition].tileSquareMeter.toString())
            parentBinding.packingSizeInput.genericNumberDecimalInputEditor.setText(myCatalogTiles[gottenPosition].packingSize.toString())
            parentBinding.marketPriceInput.genericNumberDecimalInputEditor.setText(myCatalogTiles[gottenPosition].marketPrice.toString())
            parentBinding.sellingPriceInput.genericNumberDecimalInputEditor.setText(myCatalogTiles[gottenPosition].sellingPrice.toString())
            parentBinding.warehouseNameInput.genericTextInputEditor.setText(myCatalogTiles[gottenPosition].warehouseName)
            parentBinding.phoneNumberInput.genericNumberInputEditor.setText(myCatalogTiles[gottenPosition].phoneNumber)
            parentBinding.originCountryInput.genericTextInputEditor.setText(myCatalogTiles[gottenPosition].originCountry)
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


