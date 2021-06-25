package com.chibuzo.tiler.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chibuzo.tiler.R
import com.chibuzo.tiler.databinding.ActivityNigerianTilesBinding
import com.chibuzo.tiler.model.NigerianTilesModel

class NigerianTilesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNigerianTilesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNigerianTilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nigerianTilesRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val nigerianTiles = ArrayList<NigerianTilesModel>()

        nigerianTiles.add(NigerianTilesModel("", "30 x 30", "", ""))
        nigerianTiles.add(NigerianTilesModel("", "25 x 40", "15", "1.5"))
        nigerianTiles.add(NigerianTilesModel("", "30 x 30", "", ""))
        nigerianTiles.add(NigerianTilesModel("", "30 x 30", "", ""))
        nigerianTiles.add(NigerianTilesModel("", "30 x 30", "", ""))
        nigerianTiles.add(NigerianTilesModel("", "30 x 30", "", ""))
    }
}


