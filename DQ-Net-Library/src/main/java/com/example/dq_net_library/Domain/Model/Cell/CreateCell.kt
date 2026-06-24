package com.example.dq_net_library.Domain.Model.Cell

data class CreateCell(
    val userId: String,
    val number:Int,
    val type: String,
    val value: String,
)