package com.example.dq_net_library.Domain.Model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(

    val email: String,
    val password: String,
    val userName: String,
    val avatar: String,
)