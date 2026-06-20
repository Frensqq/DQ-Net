package com.example.dq_net_library.Domain.Repository

import com.example.dq_net_library.Domain.Model.NetworkResult
import com.example.dq_net_library.Domain.Model.User.ResponseAuth
import com.example.dq_net_library.Domain.Model.User.ResponseOtpRequest
import com.example.dq_net_library.Domain.Model.User.User
import com.example.dq_net_library.Domain.Model.User.UserResponses
import java.io.File

interface Repository {
    suspend fun registration(email: String, password: String, passwordConfirm: String, username: String, avatar: File?): NetworkResult<User>
    suspend fun loginIn(email: String, password: String): NetworkResult<ResponseAuth>
    suspend fun updateProfile(userId: String, token: String, userName: String, avatarFile: File?): NetworkResult<User>
    suspend fun getUsers(filter: String? = null): NetworkResult<UserResponses>
    suspend fun getUser(id: String): NetworkResult<User>
    suspend fun deleteUser(id: String): NetworkResult<Unit>


    suspend fun otpRequest(email: String): NetworkResult<ResponseOtpRequest>
    suspend fun otpAuth(otpId: String, password: String): NetworkResult<ResponseAuth>
    suspend fun changePassword(token: String, password: String, newPassword: String): NetworkResult<Unit>
    suspend fun resetPassword(email: String): NetworkResult<Unit>

}