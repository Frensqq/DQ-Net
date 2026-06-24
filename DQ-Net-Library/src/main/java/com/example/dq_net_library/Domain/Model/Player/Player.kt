package com.example.dq_net_library.Domain.Model.Player

import kotlinx.serialization.Serializable

@Serializable
data class Player (
    val collectionId:String,
    val collectionName: String,
    val id: String,
    val userId: String,
    val BOT: Boolean,
    val state: String,
    val position: String,
    val protect: Boolean,
    val Bonus: String,
    val Event: String,
    val created: String,
    val updated: String
)