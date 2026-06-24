package com.example.dq_net_library.Domain.Model.Game

data class GameResponses (
    val page: Int,
    val perPage:Int,
    val totalPages:Int,
    val totalItems:Int,
    val items: List<Game>
)