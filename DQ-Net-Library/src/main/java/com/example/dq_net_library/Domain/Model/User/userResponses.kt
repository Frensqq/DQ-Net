package com.example.dq_net_library.Domain.Model.User

data class UserResponses(
    val page:Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<User>
)