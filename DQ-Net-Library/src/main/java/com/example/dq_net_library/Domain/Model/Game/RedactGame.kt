package com.example.dq_net_library.Domain.Model.Game

import kotlinx.serialization.Serializable

@Serializable
data class RedactGame (
    val players: List<String>,
    val type: String,
    val status: String,
    val start: String,
    val end: String,
    val countCell:Int,
    val currentPlayer: String,
)