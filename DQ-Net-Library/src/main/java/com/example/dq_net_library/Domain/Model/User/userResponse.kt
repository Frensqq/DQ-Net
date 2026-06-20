package com.example.dq_net_library.Domain.Model.User


import kotlinx.serialization.Serializable

@Serializable
data class userResponse(
    val collectionId: String,
    val collectionName: String,
    val id: String,
    val email: String,
    val userName: String,
    val avatar: String,
)