package com.chibuzo.tiler.datastore

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    companion object {
        private val chibuCatalogTiles = "chibucatalogtiles"
        private val databaseName = "computebone"
        private val databaseVersion = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create all the required tables here
        val createCatalogTilesTable = "create table if not exists $chibuCatalogTiles (" +
                "catalogTileId integer primary key autoincrement, tileName text, dimension text, " +
                "tileSquareMeter real, packingSize integer, marketPrice real, sellingPrice real, " +
                "warehouseName text, phoneNumber text, originCountry text, availability numeric, " +
                "tileImageString text)"
        db?.execSQL(createCatalogTilesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // drop all created tables here and recreate them by calling the onCreate method
        db?.execSQL("drop table if exists $chibuCatalogTiles")
        onCreate(db)
    }
}


