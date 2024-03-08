package com.vptellez.storeapp.repository.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products/categories")
    fun getCategoriesApi(): Call<List<String>>
}