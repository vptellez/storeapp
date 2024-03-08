package com.vptellez.storeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vptellez.storeapp.model.Categories
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import com.vptellez.storeapp.repository.api.ApiClient

class MainViewModel : ViewModel() {
    private val apiService = ApiClient.create()
    val productCategories = MutableLiveData<List<String>>()

    fun fetchProductCategories() {
        Log.i("STOREAPP:","fetchProductCategories")
        apiService.getCategoriesApi().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                Log.i("STOREAPP:","onResponse: " + response)
                if (response.isSuccessful) {
                    Log.i("STOREAPP:","if")
                    response.body()?.let { categories ->
                        Log.i("STOREAPP:","size: " + categories.size)
                        productCategories.value = categories
                    }
                } else {
                    Log.i("STOREAPP:","else")
                    // Manejar el error de la llamada a la API
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i("STOREAPP:","onFailure")
                // Manejar el error de la conexión
            }
        })
    }
}