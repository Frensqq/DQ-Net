package com.example.dq_net_library.Domain.Model.Player

import kotlinx.serialization.Serializable

@Serializable
data class CreatePlayer (
    val userId: String,
    val BOT: Boolean,
    val state: String,
    val position: String,
    val protect: Boolean,
    val Bonus: String,
    val Event: String,
)