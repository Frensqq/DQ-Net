package com.example.dq_net_library.Domain.Model.Game

import kotlinx.serialization.Serializable

@Serializable
data class RequestCreateGame (
    val name: String,
    val players: List<String>,
    val countPlayer: Double,      // <-- Double
    val multiplayer: Boolean,
    val type: String,
    val status: String,
    val creator: String,
    val start: String,
    val end: String,
    val countCell: Double,        // <-- Double
    val currentPlayer: String,
    val currentTurn: Double = 0.0,
    val diceValue: Double = 0.0,
    )