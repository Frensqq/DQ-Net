package com.example.dq_net_library.Domain.Model.Game

import kotlinx.serialization.Serializable

@Serializable
data class Game (
    val collectionId:String,
    val collectionName: String,
    val id: String,
    val name: String,
    val players: List<String>,
    val countPlayer:Int,
    val multiplayer: Boolean,
    val type: String,
    val status: String,
    val creator: String,
    val start: String,
    val end: String,
    val countCell:Int,
    val currentPlayer: String,
    val created: String,
    val updated: String
)