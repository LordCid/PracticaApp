package com.slashmobility.seleccion.albert.cid.data.network


import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(
    private val context: Context
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectivityException()
        }
        val request = chain.request().newBuilder().build()
        return chain.proceed(request)
    }


    private fun isConnected(): Boolean {
        val netInfo = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return netInfo.isDefaultNetworkActive
    }

}

class NoConnectivityException : IOException() {

}
