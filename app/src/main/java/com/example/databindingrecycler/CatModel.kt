package com.example.databindingrecycler

import java.io.Serializable

data class CatModel(
    val imageResId: Int,
    val name: String,
    val description: String,
    val url: String,
    var text: String = ""
) : Serializable