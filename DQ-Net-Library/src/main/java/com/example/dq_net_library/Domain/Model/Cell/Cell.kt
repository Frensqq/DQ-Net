package com.example.dq_net_library.Domain.Model.Cell

data class Cell (
    val collectionId:String,
    val collectionName: String,
    val id: String,
    val userId: String,
    val number:Int,
    val type: String,
    val value: String,
    val created: String,
    val updated: String
)