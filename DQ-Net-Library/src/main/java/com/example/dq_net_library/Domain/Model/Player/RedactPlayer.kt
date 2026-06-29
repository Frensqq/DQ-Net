package com.example.dq_net_library.Domain.Model.Player

import kotlinx.serialization.Serializable

@Serializable
data class RedactPlayer(
    val position: Int = 0,
    val shield: Boolean = false,
    val finished: Boolean = false,
)