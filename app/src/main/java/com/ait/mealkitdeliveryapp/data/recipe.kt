package com.ait.mealkitdeliveryapp.data

data class recipe (
    var uid : String = "",
    var name : String = "",
    var ingredient : String = "",
    var description: String = "",
    var instruction: String = "",
    var nutrition : String = "",
    var category : Int = -1,
    var price : Float = 0.0f,
    var imageUri : String = ""
)