package com.example.dq_net_library.Domain.Model.User

data class User(
    val collectionId: String,
    val collectionName: String,
    val id: String,
    val email: String,
    val emailVisibility: Boolean,
    val verified: Boolean,
    val userName: String,
    val avatar: String,
    val created: String,
    val updated: String
)