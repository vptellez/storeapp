package com.vptellez.storeapp.repository.api

import com.vptellez.storeapp.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/categories")
    fun getCategoriesApi(): Call<List<String>>
    @GET("products/category/{category}")
    fun getProductsByCategory(@Path("category") category: String): Call<List<Products>>
}