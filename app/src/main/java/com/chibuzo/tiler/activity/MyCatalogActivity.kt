package com.chibuzo.tiler.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chibuzo.tiler.R
import com.chibuzo.tiler.adapter.MyCatalogAdapter
import com.chibuzo.tiler.databinding.ActivityMyCatalogBinding
import com.chibuzo.tiler.model.MyCatalogModel

class MyCatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myCatalogRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.myCatalogRecycler.itemAnimator = DefaultItemAnimator()

        val myCatalogTiles = ArrayList<MyCatalogModel>()

        myCatalogTiles.add(MyCatalogModel(R.drawable.tile30x30, "30 x 30 Spanish"))
        myCatalogTiles.add(MyCatalogModel(R.drawable.tile25x40, "25 x 40 Goodwill"))
        myCatalogTiles.add(MyCatalogModel(R.drawable.tile25x50, "25 x 50 Goodwill"))
        myCatalogTiles.add(MyCatalogModel(R.drawable.tile40x40, "40 x 40 Vitrified"))
        myCatalogTiles.add(MyCatalogModel(R.drawable.tile30x60, "30 x 60 Time Floor"))
        myCatalogTiles.add(MyCatalogModel(R.drawable.tile60x60, "60 x 60 White"))

        binding.myCatalogRecycler.adapter = MyCatalogAdapter(myCatalogTiles)

        binding.addCatalogTilesButton.setOnClickListener {
            val intent = Intent(this, TilesUploadActivity::class.java)
            startActivity(intent)
        }
    }
}


