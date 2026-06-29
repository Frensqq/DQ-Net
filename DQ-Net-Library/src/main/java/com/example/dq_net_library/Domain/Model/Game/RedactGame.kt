package com.example.dq_net_library.Domain.Model.Game

import kotlinx.serialization.Serializable

@Serializable
data class RedactGame (
    val players: List<String>,
    val type: String,
    val status: String,
    val start: String,
    val end: String,
    val countCell: Double,
    val creator: String,
    val currentPlayer: String,
    val currentTurn: Double = 0.0,
    val diceValue: Double = 0.0,
)