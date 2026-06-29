package com.example.dq_net_library.Domain.Model.Player

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val collectionId: String,
    val collectionName: String,
    val id: String,
    val userId: String,
    val gameId: String,
    val isBot: Boolean = false,
    val position: Int = 0,
    val shield: Boolean = false,
    val finished: Boolean = false,
    val turnOrder: Int = 0,
    val created: String,
    val updated: String
)