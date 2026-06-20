package com.example.dq_net_library.Domain.Model

import kotlinx.serialization.Serializable


data class ErrorResponse(
    val status: Int,
    val message: String,
    val data: Map<String, Any> = emptyMap()
)