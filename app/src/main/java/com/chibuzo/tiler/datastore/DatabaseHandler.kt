package com.chibuzo.tiler.datastore

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.chibuzo.tiler.model.MyCatalogTilesModel

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    companion object {
        private const val chibuCatalogTiles = "chibucatalogtiles"
        private const val databaseName = "computebone"
        private const val databaseVersion = 3
        private const val catalogTileId = "catalogTileId"
        private const val tileName = "tileName"
        private const val dimension = "dimension"
        private const val tileSquareMeter = "tileSquareMeter"
        private const val packingSize = "packingSize"
        private const val marketPrice = "marketPrice"
        private const val sellingPrice = "sellingPrice"
        private const val warehouseName = "warehouseName"
        private const val phoneNumber = "phoneNumber"
        private const val originCountry = "originCountry"
        private const val availability = "availability"
        private const val tileImageName = "tileImageName"
        private const val tileImageWidth = "tileImageWidth"
        private const val tileImageHeight= "tileImageHeight"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create all the required tables here
        val createCatalogTilesTable = "create table if not exists $chibuCatalogTiles (" +
                "catalogTileId integer primary key autoincrement, tileName text, dimension text, " +
                "tileSquareMeter real, packingSize integer, marketPrice real, sellingPrice real, " +
                "warehouseName text, phoneNumber text, originCountry text, availability numeric, " +
                "tileImageName text, tileImageWidth integer, tileImageHeight integer)"
        db?.execSQL(createCatalogTilesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // drop all created tables here and recreate them by calling the onCreate method
        db?.execSQL("drop table if exists $chibuCatalogTiles")
        onCreate(db)
    }

    fun addChibuCatalogTile(catalogTilesModel: MyCatalogTilesModel): Long {
        val dataStore = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(tileName, catalogTilesModel.tileName)
        contentValues.put(dimension, catalogTilesModel.dimension)
        contentValues.put(tileSquareMeter, catalogTilesModel.tileSquareMeter)
        contentValues.put(packingSize, catalogTilesModel.packingSize)
        contentValues.put(marketPrice, catalogTilesModel.marketPrice)
        contentValues.put(sellingPrice, catalogTilesModel.sellingPrice)
        contentValues.put(warehouseName, catalogTilesModel.warehouseName)
        contentValues.put(phoneNumber, catalogTilesModel.phoneNumber)
        contentValues.put(originCountry, catalogTilesModel.originCountry)
        contentValues.put(availability, catalogTilesModel.availability)
        contentValues.put(tileImageName, catalogTilesModel.tileImageName)
        contentValues.put(tileImageWidth, catalogTilesModel.tileImageWidth)
        contentValues.put(tileImageHeight, catalogTilesModel.tileImageHeight)

        val success = dataStore.insert(chibuCatalogTiles, null, contentValues)
        dataStore.close()
        return success
    }

    fun getAllCatalogTiles(): ArrayList<MyCatalogTilesModel> {
        val chibuCatalogTilesList = ArrayList<MyCatalogTilesModel>()
        val query = "select * from $chibuCatalogTiles order by $catalogTileId desc"
        val dataStore = readableDatabase
        val cursor: Cursor?

        try {
            cursor = dataStore.rawQuery(query, null)
        } catch (exception: SQLiteException) {
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val catalogTilesModel = MyCatalogTilesModel(
                    catalogTileId = cursor.getInt(cursor.getColumnIndex(catalogTileId)),
                    tileName = cursor.getString(cursor.getColumnIndex(tileName)),
                    dimension = cursor.getString(cursor.getColumnIndex(dimension)),
                    tileSquareMeter = cursor.getFloat(cursor.getColumnIndex(tileSquareMeter)),
                    packingSize = cursor.getInt(cursor.getColumnIndex(packingSize)),
                    marketPrice = cursor.getDouble(cursor.getColumnIndex(marketPrice)),
                    sellingPrice = cursor.getDouble(cursor.getColumnIndex(sellingPrice)),
                    warehouseName = cursor.getString(cursor.getColumnIndex(warehouseName)),
                    phoneNumber = cursor.getString(cursor.getColumnIndex(phoneNumber)),
                    originCountry = cursor.getString(cursor.getColumnIndex(originCountry)),
                    availability = cursor.getInt(cursor.getColumnIndex(availability)),
                    tileImageName = cursor.getString(cursor.getColumnIndex(tileImageName)),
                    tileImageWidth = cursor.getInt(cursor.getColumnIndex(tileImageWidth)),
                    tileImageHeight = cursor.getInt(cursor.getColumnIndex(tileImageHeight))
                )

                chibuCatalogTilesList.add(catalogTilesModel)
            } while (cursor.moveToNext())
        }

        return chibuCatalogTilesList
    }

    fun updateCatalogTiles(catalogTilesModel: MyCatalogTilesModel): Int {
        val dataStore = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(catalogTileId, catalogTilesModel.catalogTileId)
        contentValues.put(tileName, catalogTilesModel.tileName)
        contentValues.put(dimension, catalogTilesModel.dimension)
        contentValues.put(tileSquareMeter, catalogTilesModel.tileSquareMeter)
        contentValues.put(packingSize, catalogTilesModel.packingSize)
        contentValues.put(marketPrice, catalogTilesModel.marketPrice)
        contentValues.put(sellingPrice, catalogTilesModel.sellingPrice)
        contentValues.put(warehouseName, catalogTilesModel.warehouseName)
        contentValues.put(phoneNumber, catalogTilesModel.phoneNumber)
        contentValues.put(originCountry, catalogTilesModel.originCountry)
        contentValues.put(availability, catalogTilesModel.availability)
        contentValues.put(tileImageName, catalogTilesModel.tileImageName)
        contentValues.put(tileImageWidth, catalogTilesModel.tileImageWidth)
        contentValues.put(tileImageHeight, catalogTilesModel.tileImageHeight)

        val success = dataStore.update(chibuCatalogTiles, contentValues,
            "$catalogTileId = ${catalogTilesModel.catalogTileId}", null)
        dataStore.close()
        return success
    }

    fun deleteCatalogTiles(catalogTilesModel: MyCatalogTilesModel): Int {
        val dataStore = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(catalogTileId, catalogTilesModel.catalogTileId)

        val success = dataStore.delete(chibuCatalogTiles,
            "$catalogTileId = ${catalogTilesModel.catalogTileId}", null)
        dataStore.close()
        return success
    }
}


