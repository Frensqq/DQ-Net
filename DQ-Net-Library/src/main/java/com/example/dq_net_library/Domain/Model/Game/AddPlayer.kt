package com.example.dq_net_library.Domain.Model.Game

import kotlinx.serialization.Serializable

@Serializable
data class AddPlayer(
    val players: List<String>,
    val countPlayer: Double,
)