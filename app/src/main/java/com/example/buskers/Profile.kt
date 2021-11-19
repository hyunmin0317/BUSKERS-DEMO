package com.example.buskers

import java.io.Serializable

class Profile (
    val id : Int? =null,
    val owner : String? = null,
    var image : String? = null
): Serializable