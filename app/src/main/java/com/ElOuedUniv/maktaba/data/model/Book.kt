package com.ElOuedUniv.maktaba.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Book(
    val isbn: String,
    val title: String,
    @SerialName("nb_pages")
    val nbPages: Int,
    @SerialName("image_url")
    val imageUrl: String? = null
)