package com.example.dq_net_library.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkMonitor(private  val  context: Context): NetworkConnected {
    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network)?:return false
        return  capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}