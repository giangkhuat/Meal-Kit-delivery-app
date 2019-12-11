package com.ait.mealkitdeliveryapp.data

data class order (
    var uid : String = "",
    var recipeName : String = "",
    var quantity : Int = 0,
    var cost : Float = 0.0f,
    var address : String = "",
    var dateOrder : String = ""
)