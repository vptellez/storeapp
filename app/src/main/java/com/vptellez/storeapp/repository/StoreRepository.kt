package com.vptellez.storeapp.repository

import android.util.Log
import com.vptellez.storeapp.model.Categories
import com.vptellez.storeapp.repository.api.ApiClient
import com.vptellez.storeapp.repository.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository(private val apiService: ApiService) {
    fun getCategories(callback: (List<String>?) -> Unit) {
        apiService.getCategoriesApi().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                callback(null)
            }
        })
    }
}

/*
class StoreRepository constructor(private val retrofitService: ApiService) {
    fun getCategories(callback: (List<String>?) -> Unit) {
        apiService.getCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                callback(null)
            }
        })
    }
}

 */

/*
class StoreRepository {
    private val apiService = ApiClient.apiService

    fun getCategories() : Categories {
        Log.i("STOREAPP: ","getCategories StoreRepository")
        return apiService.getCategories()
    }
}
*/