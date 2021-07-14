package com.chibuzo.tiler.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chibuzo.tiler.R
import com.chibuzo.tiler.adapter.MyCatalogAdapter
import com.chibuzo.tiler.databinding.ActivityMyCatalogBinding
import com.chibuzo.tiler.datastore.DatabaseHandler
import java.lang.StringBuilder

class MyCatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCatalogBinding
    private var sharedPreferences: SharedPreferences? = null
    private var sharedPreferencesEditor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = applicationContext.getSharedPreferences(getString(R.string.shared_preferences_key), MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences?.edit()

        binding = ActivityMyCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myCatalogMenuLayout.cancelDeleteButton.genericButtonLabel.text = "Cancel"
        binding.myCatalogMenuLayout.continueDeleteButton.genericButtonLabel.text = "Delete"
        binding.myCatalogMenuLayout.tileEditMenu.genericMenuText.text = "Edit Tile"
        binding.myCatalogMenuLayout.tileDeleteMenu.genericMenuText.text = "Delete Tile"
        binding.myCatalogMenuLayout.tileAvailabilityMenu.genericSwitchInputLabel.text = "Available"
        binding.tileNameInput.genericTextInputLabel.text = "Tile Name"
        binding.dimensionInputLabel.text = "Dimension"
        binding.toolbarMenuLayout.toolbarCloseMenu.genericMenuText.text = "Close"
        binding.toolbarMenuLayout.toolbarShuffleTileMenu.genericMenuText.text = "Shuffle Tiles"
        binding.toolbarMenuLayout.toolbarOrderTileMenu.genericMenuText.text = "Order Tiles"
        binding.squareMeterInput.genericNumberDecimalInputLabel.text = "Square Meter Per Carton"
        binding.packingSizeInput.genericNumberDecimalInputLabel.text = "Packing Size Per Carton"
        binding.marketPriceInput.genericNumberDecimalInputLabel.text = "Market Price"
        binding.sellingPriceInput.genericNumberDecimalInputLabel.text = "Selling Price"
        binding.warehouseNameInput.genericTextInputLabel.text = "Warehouse"
        binding.phoneNumberInput.genericNumberInputLabel.text = "Phone Number"
        binding.originCountryInput.genericTextInputLabel.text = "Made In"
        binding.updateCustomTile.genericButtonLabel.text = "Update Tile"

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        binding.myCatalogRecycler.layoutManager = staggeredGridLayoutManager
        binding.myCatalogRecycler.itemAnimator = DefaultItemAnimator()

        val databaseHandler = DatabaseHandler(this)
        var myCatalogTiles = databaseHandler.getAllCatalogTiles()

        val sortTilesMenuType = sharedPreferences?.getString(getString(R.string.order_tile_settings_key), "")

        if (sortTilesMenuType.equals(getString(R.string.shuffle_tile_settings))) {
            myCatalogTiles.shuffle()
        }

        binding.myCatalogRecycler.adapter = MyCatalogAdapter(myCatalogTiles, binding)

        binding.toolbarMenuLayout.toolbarShuffleTileMenu.genericMenuLayout.setOnClickListener {
            binding.toolbarMenuLayout.toolbarMenuLayout.visibility = View.GONE
            sharedPreferencesEditor?.putString(getString(R.string.order_tile_settings_key), getString(R.string.shuffle_tile_settings))?.apply()
            myCatalogTiles.shuffle()
            binding.myCatalogRecycler.adapter?.notifyDataSetChanged()
        }

        binding.toolbarMenuLayout.toolbarOrderTileMenu.genericMenuLayout.setOnClickListener {
            binding.toolbarMenuLayout.toolbarMenuLayout.visibility = View.GONE
            sharedPreferencesEditor?.putString(getString(R.string.order_tile_settings_key), getString(R.string.order_tile_settings))?.apply()
            myCatalogTiles.clear()
            myCatalogTiles.addAll(databaseHandler.getAllCatalogTiles())
            binding.myCatalogRecycler.adapter?.notifyDataSetChanged()
        }

        binding.toolbarMenuLayout.toolbarCloseMenu.genericMenuLayout.setOnClickListener {
            binding.toolbarMenuLayout.toolbarMenuLayout.visibility = View.GONE
        }

        binding.toolbarMenuLayout.toolbarMenuLayout.setOnClickListener {
            binding.toolbarMenuLayout.toolbarMenuLayout.visibility = View.GONE
        }

        binding.includeToolbarLayout.genericToolbarLayout.setOnClickListener {
            if (binding.toolbarMenuLayout.toolbarMenuLayout.isVisible) {
                binding.toolbarMenuLayout.toolbarMenuLayout.visibility = View.GONE
            } else {
                binding.toolbarMenuLayout.toolbarMenuLayout.visibility = View.VISIBLE
            }
        }

        binding.addCatalogTilesButton.setOnClickListener {
            val intent = Intent(this, TilesUploadActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (binding.tileCatalogEditorLayout.isVisible) {
            binding.tileCatalogEditorLayout.visibility = View.GONE
        } else {
            finish()
        }
    }
}


