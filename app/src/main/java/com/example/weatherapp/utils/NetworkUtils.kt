package com.example.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

object NetworkUtils {
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivityManager == null) {
            Log.d("NetworkCheck", "No ConnectivityManager found.")
            return false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network: Network? = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            val isOnline = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
            Log.d("NetworkCheck", "Network is online: $isOnline")
            return isOnline
        } else { // OLD API
            val networkInfo = connectivityManager.activeNetworkInfo
            val isOnline = networkInfo?.isConnected == true
            Log.d("NetworkCheck", "Network is online: $isOnline")
            return isOnline
        }
    }
}