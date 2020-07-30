package com.slashmobility.seleccion.albert.cid.data


import com.slashmobility.seleccion.albert.cid.data.model.GroupNetworkModel
import com.slashmobility.seleccion.albert.cid.domain.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("groups.json")
    fun getGroups(): Call<List<GroupNetworkModel>>

    @GET("images/{id}.json")
    fun getImages(@Path("id") id: Int): Call<List<String>>


    companion object {
        fun create(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}