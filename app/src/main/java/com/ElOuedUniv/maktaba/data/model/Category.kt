package com.ElOuedUniv.maktaba.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Category(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    @SerialName("icon_res")
    val iconRes: Int = 17301569 // Default android.R.drawable.ic_menu_help if missing
)