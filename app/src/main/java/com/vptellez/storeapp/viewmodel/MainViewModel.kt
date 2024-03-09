package com.vptellez.storeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vptellez.storeapp.model.Products
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import com.vptellez.storeapp.repository.api.ApiClient
class MainViewModel : ViewModel() {
    private val apiService = ApiClient.create()
    val productCategories = MutableLiveData<List<String>>()
    val listProductsByCategory = MutableLiveData<List<Products>>()
    fun fetchProductCategories() {
        apiService.getCategoriesApi().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let { categories ->
                        productCategories.value = categories
                    }
                } else {
                    Log.i("STORE APP:","else")
                    // Error de la llama a la API
                }
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i("STORE APP:","onFailure")
                // Error de la connexion
            }
        })
    }
    fun fetchProductsByCategory(category: String) {
        Log.i("STORE APP:","category ${category.lowercase()}")
        apiService.getProductsByCategory(category.lowercase()).enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                if (response.isSuccessful) {
                    response.body()?.let { products ->
                        Log.i("STORE APP:","products ${products.size}")
                        listProductsByCategory.value = products
                    }
                    Log.i("STORE APP:","listProductsByCategory ${listProductsByCategory.value}")
                } else {
                    Log.i("STORE APP:","else")
                }
            }
            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Log.i("STORE APP:","onFailure")
            }
        })
    }
}