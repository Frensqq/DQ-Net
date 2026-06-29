package com.example.dq_net_library.Domain.Model.Cell

import com.example.dq_net_library.Domain.Model.Game.Game
import kotlinx.serialization.Serializable

@Serializable
data class ResponsesCell (
    val page: Int,
    val perPage:Int,
    val totalPages:Int,
    val totalItems:Int,
    val items: List<Cell>
)