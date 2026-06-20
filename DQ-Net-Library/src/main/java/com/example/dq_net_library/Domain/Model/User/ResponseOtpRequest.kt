package com.example.dq_net_library.Domain.Model.User

import kotlinx.serialization.Serializable

@Serializable
data class ResponseOtpRequest(
    val otpId: String
)