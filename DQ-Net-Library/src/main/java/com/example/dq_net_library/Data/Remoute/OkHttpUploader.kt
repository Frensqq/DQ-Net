package com.example.dq_net_library.Data.Remoute

import android.util.Log
import com.example.dq_net_library.Domain.Model.User.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.jvm.java

class OkHttpUploader {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    private suspend fun executeRequest(
        url: String,
        token: String,
        method: String,
        body: okhttp3.RequestBody,
        logMessage: String
    ): String = withContext(Dispatchers.IO) {
        Log.d("OkHttpUploader", logMessage)

        val request = Request.Builder()
            .url(url)
            .method(method, body)
            .addHeader("Authorization", "Bearer $token")
            .build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body?.string() ?: ""
            Log.d("OkHttpUploader", "Response code: ${response.code}")

            if (!response.isSuccessful) {
                Log.e("OkHttpUploader", "Error: $responseBody")
                throw Exception("Request failed: ${response.code}")
            }
            responseBody
        }
    }


    suspend fun registration(
        baseUrl: String,
        email: String,
        password: String,
        passwordConfirm: String,
        username: String,
        avatarFile: File?
    ): User {
        val url = "${baseUrl}collections/users/records"

        val body = if (avatarFile?.exists() == true) {
            Log.d("OkHttpUploader", "With avatar, size: ${avatarFile.length()} bytes")
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .addFormDataPart("passwordConfirm", passwordConfirm)
                .addFormDataPart("userName", username)
                .addFormDataPart("avatar", avatarFile.name, avatarFile.asRequestBody("image/jpeg".toMediaType()))
                .build()
        } else {
            Log.d("OkHttpUploader", "Without avatar (JSON)")
            val data = mapOf(
                "email" to email,
                "password" to password,
                "passwordConfirm" to passwordConfirm,
                "userName" to username,
            )
            gson.toJson(data).toRequestBody("application/json".toMediaType())
        }

        val response = executeRequest(url, "","POST", body, "Creating user: $username")
        return gson.fromJson(response, User::class.java)
    }


    suspend fun updateUser(
        baseUrl: String,
        userId: String,
        token: String,
        userName: String,
        avatarFile: File?
    ): User {
        val url = "${baseUrl}collections/users/records/$userId"

        val body = if (avatarFile?.exists() == true) {
            Log.d("OkHttpUploader", "With avatar, size: ${avatarFile.length()} bytes")
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userName", userName)
                .addFormDataPart("avatar", avatarFile.name, avatarFile.asRequestBody("image/jpeg".toMediaType()))
                .build()
        } else {
            Log.d("OkHttpUploader", "Without avatar (JSON)")
            gson.toJson(mapOf("userName" to userName)).toRequestBody("application/json".toMediaType())
        }
        val response = executeRequest(url, token, "PATCH", body, "Updating user: $userId")
        return gson.fromJson(response, User::class.java)
    }





}