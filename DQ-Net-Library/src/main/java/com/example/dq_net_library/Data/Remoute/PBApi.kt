package com.example.dq_net_library.Data.Remoute

import com.example.dq_net_library.Domain.Model.Cell.Cell
import com.example.dq_net_library.Domain.Model.Cell.CreateCell
import com.example.dq_net_library.Domain.Model.Cell.ResponsesCell
import com.example.dq_net_library.Domain.Model.Game.AddPlayer
import com.example.dq_net_library.Domain.Model.Game.Game
import com.example.dq_net_library.Domain.Model.Game.GameResponses
import com.example.dq_net_library.Domain.Model.Game.RedactGame
import com.example.dq_net_library.Domain.Model.Game.RequestCreateGame
import com.example.dq_net_library.Domain.Model.Player.CreatePlayer
import com.example.dq_net_library.Domain.Model.Player.Player
import com.example.dq_net_library.Domain.Model.Player.RedactPlayer
import com.example.dq_net_library.Domain.Model.Player.ResponsesPlayers
import com.example.dq_net_library.Domain.Model.User.ResponseAuth
import com.example.dq_net_library.Domain.Model.User.ResponseOtpRequest
import com.example.dq_net_library.Domain.Model.User.User
import com.example.dq_net_library.Domain.Model.User.UserResponses
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.io.File

class PBApi(
    private val client: HttpClient,
    private val baseUrl: String
) {

    private fun buildUrl(path: String): String = "$baseUrl$path"

    private val okHttpUploader = OkHttpUploader()

    suspend fun registration(
        email: String,
        password: String,
        passwordConfirm: String,
        username: String,
        avatar: File?
    ): User {
        return okHttpUploader.registration(
            baseUrl,
            email,
            password,
            passwordConfirm,
            username,
            avatar)
    }

    suspend fun loginIn(email: String, password: String): ResponseAuth {
        return client.post(buildUrl("collections/users/auth-with-password")) {
            contentType(ContentType.Application.Json)
            setBody(mapOf("identity" to email, "password" to password))
        }.body()
    }

    suspend fun updateUser(
        userId: String,
        token: String,
        userName: String,
        avatarFile: File?
    ): User {
        return okHttpUploader.updateUser(
            baseUrl = baseUrl,
            userId = userId,
            token = token,
            userName = userName,
            avatarFile = avatarFile
        )
    }

    suspend fun getUsers(filter: String? = null): UserResponses {
        return client.get(buildUrl("collections/users/records")) {
            filter?.let { parameter("filter", it) }
        }.body()
    }

    suspend fun getUser(id: String): User {
        return client.get(buildUrl("collections/users/records/$id")).body()
    }

    suspend fun deleteUser(id: String) {
        client.delete(buildUrl("collections/users/records/$id"))
    }

    suspend fun otpRequest(email: String): ResponseOtpRequest {
        return client.post(buildUrl("collections/users/request-otp")) {
            contentType(ContentType.Application.Json)
            setBody(mapOf("email" to email))
        }.body()
    }

    suspend fun otpAuth(otpId:String, otpCode: String): ResponseAuth {
        return client.post(buildUrl("collections/users/auth-with-otp")) {
            contentType(ContentType.Application.Json)
            setBody(mapOf(
                "otpId" to otpId ,
                "password" to otpCode
            ))
        }.body()
    }

    suspend fun resetPassword(email: String) {
        client.post(buildUrl("collections/users/request-password-reset")) {
            contentType(ContentType.Application.Json)
            setBody(mapOf("email" to email))
        }
    }

    suspend fun changePassword(token: String, password: String, newPassword: String) {
        client.post(buildUrl("collections/users/confirm-password-reset")) {
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "token" to token,
                    "password" to password,
                    "passwordConfirm" to newPassword
                )
            )
        }
    }


    fun getImageUrl(collectionId: String, recordId: String, fileName: String): String {
        return "$baseUrl" + "files/$collectionId/$recordId/$fileName"
    }


    //game
    suspend fun createGame(request: RequestCreateGame): Game{
        return client.post ( buildUrl("collections/Game/records")){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getGame(id: String): Game{
        return client.get (
            buildUrl("collections/Game/records/$id")){
        }.body()
    }

    suspend fun getGames(filter: String? = null): GameResponses{
        return client.get (
            buildUrl("collections/Game/records")){
            filter?.let { parameter("filter", it) }
        }.body()
    }

    suspend fun addPlayerGames(id: String, request: AddPlayer): Game{
        return client.patch (
            buildUrl("collections/Game/records/$id")){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }


    suspend fun patchGames(id: String, request: RedactGame): Game{
        return client.patch (
            buildUrl("collections/Game/records/$id")){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    //Players

    suspend fun createPlayer(request: CreatePlayer): Player{
        return client.post(buildUrl("collections/Player/records")){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getPlayer(id: String): Player{
        return client.get (
            buildUrl("collections/Player/records/$id")){
        }.body()
    }

    suspend fun getPlayers(filter: String? = null): ResponsesPlayers{
        return client.get (
            buildUrl("collections/Player/records")){
            filter?.let { parameter("filter", it) }
        }.body()
    }

    suspend fun deletePlayer(id: String) {
        client.delete(buildUrl("collections/Player/records/$id"))
    }

    suspend fun patchPlayer(id: String, request: RedactPlayer): Player{
        return client.patch (
            buildUrl("collections/Player/records/$id")){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getCell(id: String): Cell{
        return client.get (
            buildUrl("collections/Cell/records/$id")){
        }.body()
    }

    suspend fun getCells(
        filter: String? = null,
        page: Int? = null,
        perPage: Int? = null
    ): ResponsesCell {
        return client.get(buildUrl("collections/Cell/records")) {
            filter?.let { parameter("filter", it) }
            page?.let { parameter("page", it) }
            perPage?.let { parameter("perPage", it) }
        }.body()
    }

    suspend fun deleteCell(id: String) {
        client.delete(buildUrl("collections/Cell/records/$id"))
    }

    suspend fun createCell(request: CreateCell): Cell{
        return client.post(buildUrl("collections/Cell/records")){
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

}