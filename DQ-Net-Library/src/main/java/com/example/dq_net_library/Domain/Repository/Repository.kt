package com.example.dq_net_library.Domain.Repository

import com.example.dq_net_library.Domain.Model.Cell.Cell
import com.example.dq_net_library.Domain.Model.Cell.CreateCell
import com.example.dq_net_library.Domain.Model.Cell.ResponsesCell
import com.example.dq_net_library.Domain.Model.Game.AddPlayer
import com.example.dq_net_library.Domain.Model.Game.Game
import com.example.dq_net_library.Domain.Model.Game.GameResponses
import com.example.dq_net_library.Domain.Model.Game.RedactGame
import com.example.dq_net_library.Domain.Model.Game.RequestCreateGame
import com.example.dq_net_library.Domain.Model.NetworkResult
import com.example.dq_net_library.Domain.Model.Player.CreatePlayer
import com.example.dq_net_library.Domain.Model.Player.Player
import com.example.dq_net_library.Domain.Model.Player.RedactPlayer
import com.example.dq_net_library.Domain.Model.Player.ResponsesPlayers
import com.example.dq_net_library.Domain.Model.User.ResponseAuth
import com.example.dq_net_library.Domain.Model.User.ResponseOtpRequest
import com.example.dq_net_library.Domain.Model.User.User
import com.example.dq_net_library.Domain.Model.User.UserResponses
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

    fun getImageUrl(collectionId: String, recordId: String, fileName: String): String


    //game
    suspend fun createGame(request: RequestCreateGame): NetworkResult<Game>
    suspend fun getGame(id: String): NetworkResult<Game>
    suspend fun getGames(filter: String? = null): NetworkResult<GameResponses>
    suspend fun addPlayerGames(id: String, request: AddPlayer): NetworkResult<Game>
    suspend fun patchGames(id: String, request: RedactGame): NetworkResult<Game>

    //Player
    suspend fun createPlayer(request: CreatePlayer): NetworkResult<Player>
    suspend fun getPlayer(id: String): NetworkResult<Player>
    suspend fun getPlayers(filter: String? = null): NetworkResult<ResponsesPlayers>
    suspend fun deletePlayer(id: String): NetworkResult<Unit>
    suspend fun patchPlayer(id: String, request: RedactPlayer): NetworkResult<Player>

    //Cell
    suspend fun getCell(id: String): NetworkResult<Cell>


    suspend fun getCells(
        filter: String? = null,
        page: Int? = null,
        perPage: Int? = null
    ):NetworkResult<ResponsesCell>


    suspend fun deleteCell(id: String): NetworkResult<Unit>
    suspend fun createCell(request: CreateCell): NetworkResult<Cell>
}