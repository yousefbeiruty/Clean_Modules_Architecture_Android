package com.pioneers.data.model

data class EventModelItem(
    val date: String,
    val date_to: String,
    val description: String,
    val id: String,
    val is_conference: Boolean,
    val link: String,
    val name: String,
    val proof_image_link: String
)