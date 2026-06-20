package com.example.dq_net_library.Data.Repository

import android.content.Context
import com.example.dq_net_library.Data.Remoute.PBApi
import com.example.dq_net_library.Domain.Model.ErrorResponse
import com.example.dq_net_library.Domain.Model.NetworkResult
import com.example.dq_net_library.Domain.Model.User.ResponseAuth
import com.example.dq_net_library.Domain.Model.User.ResponseOtpRequest
import com.example.dq_net_library.Domain.Model.User.User
import com.example.dq_net_library.Domain.Model.User.UserResponses
import com.example.dq_net_library.Domain.Repository.Repository
import com.example.dq_net_library.Network.NetworkConnected
import io.ktor.client.call.body
import java.io.File
import java.io.IOException

class PBRepositoryImpl(
    private val api: PBApi,
    private val networkMonitor: NetworkConnected,
    private val context: Context
) : Repository {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
        if (!networkMonitor.isConnected()) {
            return NetworkResult.NoInternet
        }

        return try {
            NetworkResult.Success(apiCall())
        } catch (e: IOException) {
            NetworkResult.NoInternet
        } catch (e: io.ktor.client.plugins.ClientRequestException) {
            NetworkResult.Error(
                ErrorResponse(
                    status = e.response.status.value,
                    message = e.message ?: "Client error",
                    data = mapOf("raw" to (e.response.body() ?: ""))
                )
            )
        } catch (e: io.ktor.client.plugins.ServerResponseException) {
            NetworkResult.Error(
                ErrorResponse(
                    status = e.response.status.value,
                    message = e.message ?: "Server error",
                    data = mapOf("raw" to (e.response.body() ?: ""))
                )
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                ErrorResponse(
                    status = -1,
                    message = e.message ?: "Unknown error"
                )
            )
        }
    }


    override suspend fun registration(
        email: String,
        password: String,
        passwordConfirm: String,
        username: String,
        avatar: File?
    ): NetworkResult<User> =
        safeApiCall { api.registration(
            email,
            password,
            passwordConfirm,
            username,
            avatar)
        }

    override suspend fun loginIn(email: String, password: String): NetworkResult<ResponseAuth> =
        safeApiCall{ api.loginIn(email,password) }

    override suspend fun getUser(id: String): NetworkResult<User> =
        safeApiCall {api.getUser(id) }

    override suspend fun getUsers(filter: String?): NetworkResult<UserResponses> =
        safeApiCall { api.getUsers(filter) }

    override suspend fun updateProfile(
        userId: String,
        token: String,
        userName: String,
        avatarFile: File?
    ): NetworkResult<User> =
        safeApiCall { api.updateUser(
            userId,
            token,
            userName,
            avatarFile
        ) }

    override suspend fun otpAuth(otpId: String, password: String): NetworkResult<ResponseAuth> =
        safeApiCall { api.otpAuth(
            otpId,
            password
        )
    }

    override suspend fun otpRequest(email: String): NetworkResult<ResponseOtpRequest> =
        safeApiCall {
            api.otpRequest(email)
        }

    override suspend fun resetPassword(email: String): NetworkResult<Unit> =
        safeApiCall {
            api.resetPassword(email)
    }

    override suspend fun changePassword(
        token: String,
        password: String,
        newPassword: String
    ): NetworkResult<Unit> =
        safeApiCall {
            api.changePassword(
                token,
                password,
                newPassword
            )
        }


    override suspend fun deleteUser(id: String): NetworkResult<Unit> = safeApiCall {
        api.deleteUser(id)
    }

}