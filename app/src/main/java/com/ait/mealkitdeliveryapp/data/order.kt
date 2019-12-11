package com.ait.mealkitdeliveryapp.data

data class order (
    var uid : String = "",
    var recipeName : String,
    var quantity : Int,
    var cost : Float,
    var address : String,
    var dateOrder : String
)