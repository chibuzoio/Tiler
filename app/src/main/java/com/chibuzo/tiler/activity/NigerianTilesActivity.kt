package com.chibuzo.tiler.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chibuzo.tiler.R
import com.chibuzo.tiler.adapter.NigerianTilesAdapter
import com.chibuzo.tiler.databinding.ActivityNigerianTilesBinding
import com.chibuzo.tiler.model.NigerianTilesModel

class NigerianTilesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNigerianTilesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNigerianTilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nigerianTilesRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.nigerianTilesRecycler.itemAnimator = DefaultItemAnimator()

        val nigerianTiles = ArrayList<NigerianTilesModel>()

        nigerianTiles.add(NigerianTilesModel(R.drawable.tile30x30, "30 x 30", "17", "1.53"))
        nigerianTiles.add(NigerianTilesModel(R.drawable.tile25x40, "25 x 40", "15", "1.5"))
        nigerianTiles.add(NigerianTilesModel(R.drawable.tile25x50, "25 x 50", "12", "1.5"))
        nigerianTiles.add(NigerianTilesModel(R.drawable.tile40x40, "40 x 40", "12", "1.92"))
        nigerianTiles.add(NigerianTilesModel(R.drawable.tile30x60, "30 x 60", "9", "1.62"))
        nigerianTiles.add(NigerianTilesModel(R.drawable.tile60x60, "60 x 60", "4", "1.44"))

        val nigerianTilesAdapter = NigerianTilesAdapter(nigerianTiles)
        binding.nigerianTilesRecycler.adapter = nigerianTilesAdapter
    }
}


