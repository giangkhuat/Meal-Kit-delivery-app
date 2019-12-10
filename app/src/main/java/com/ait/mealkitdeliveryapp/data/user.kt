package com.ait.mealkitdeliveryapp.data


data class user (
    var username : String = "",
    var contactEmail : String = "",
    var address : String = "",
    var phoneNumber : String = "",
    var orderMap : HashMap<String, Int> = HashMap<String, Int> ()
)