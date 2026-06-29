package com.example.dq_net_library.Domain.Model.Cell

import kotlinx.serialization.Serializable

@Serializable
data class Cell (
    val collectionId:String,
    val collectionName: String,
    val id: String,
    val gameId: String,
    val number:Int,
    val row: Int,
    val col: Int,
    val type: String,
    val value: String,
    val created: String,
    val updated: String
)