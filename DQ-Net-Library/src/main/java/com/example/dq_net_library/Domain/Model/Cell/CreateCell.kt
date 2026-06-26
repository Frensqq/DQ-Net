package com.example.dq_net_library.Domain.Model.Cell

import kotlinx.serialization.Serializable

@Serializable
data class CreateCell(
    val gameId: String,
    val number:Int,
    val type: String,
    val row: Int,
    val col: Int,
    val value: String,
)