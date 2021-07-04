package com.chibuzo.tiler.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chibuzo.tiler.adapter.MyCatalogAdapter
import com.chibuzo.tiler.databinding.ActivityMyCatalogBinding
import com.chibuzo.tiler.datastore.DatabaseHandler

class MyCatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myCatalogMenuLayout.tileEditMenu.genericMenuText.text = "Edit Tile"
        binding.myCatalogMenuLayout.tileDeleteMenu.genericMenuText.text = "Delete Tile"
        binding.myCatalogMenuLayout.tileAvailabilityMenu.genericSwitchInputLabel.text = "Available"

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        binding.myCatalogRecycler.layoutManager = staggeredGridLayoutManager
        binding.myCatalogRecycler.itemAnimator = DefaultItemAnimator()

        val databaseHandler = DatabaseHandler(this)
        val myCatalogTiles = databaseHandler.getAllCatalogTiles()

        binding.myCatalogRecycler.adapter = MyCatalogAdapter(myCatalogTiles, binding)

        binding.addCatalogTilesButton.setOnClickListener {
            val intent = Intent(this, TilesUploadActivity::class.java)
            startActivity(intent)
        }
    }
}


