package com.chibuzo.tiler.model

data class MyCatalogTilesModel(var catalogTileId: Int,
                               var tileName: String,
                               var dimension: String,
                               var tileSquareMeter: Float,
                               var packingSize: Int,
                               var marketPrice: Double,
                               var sellingPrice: Double,
                               var warehouseName: String,
                               var phoneNumber: String,
                               var originCountry: String,
                               var availability: Boolean,
                               var tileImageName: String,
                               var tileImageWidth: Int,
                               var tileImageHeight: Int)


