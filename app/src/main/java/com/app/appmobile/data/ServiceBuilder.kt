package com.app.appmobile.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    const val baseURL = "https://next-js-todo-ebon.vercel.app"
    //const val baseURL = "http://192.168.0.15:3000"
    private const val apiURL = "$baseURL/api/"


    private val retrofit =
        Retrofit.Builder()
            .baseUrl(apiURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}