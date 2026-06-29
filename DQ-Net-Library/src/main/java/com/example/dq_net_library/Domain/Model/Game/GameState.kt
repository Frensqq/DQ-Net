package com.example.dq_net_library.Domain.Model.Game

import com.example.dq_net_library.Domain.Model.Cell.Cell
import com.example.dq_net_library.Domain.Model.Player.Player

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val game: Game? = null,
    val players: List<Player> = emptyList(),
    val currentPlayer: Player? = null,
    val cells: List<Cell> = emptyList(),
    val isMyTurn: Boolean = false,
    val isHost: Boolean = false,
    val diceValue: Int = 0,
    val canRollDice: Boolean = true,
    val winner: Player? = null
)