package com.example.dq_net_library.Domain.Model.User

data class UserRequest(

    val email: String,
    val password: String,
    val userName: String,
    val avatar: String,
)