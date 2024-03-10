package com.vptellez.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vptellez.storeapp.model.CategoryModel
import com.vptellez.storeapp.model.Products
import com.vptellez.storeapp.repository.AppDatabase
import com.vptellez.storeapp.repository.StoreRepository
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import com.vptellez.storeapp.repository.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val apiService = ApiClient.create()
    val productCategories = MutableLiveData<List<String>>()
    val listProductsByCategory = MutableLiveData<List<Products>>()

    private val repository: StoreRepository
    val allCategories : LiveData<List<CategoryModel>>

    init {
        val dao = AppDatabase.getDatabase(application).categoryDao()
        repository = StoreRepository(dao)
        allCategories = repository.allCategories
    }

    fun fetchProductCategories() {
        apiService.getCategoriesApi().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let { categories ->
                        productCategories.value = categories

                        val categoryModels: List<CategoryModel> = categories.map { CategoryModel(it) }
                        insertCategory(categoryModels)
                    }
                } else {
                    // Error de la llama a la API
                }
            }
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                // Error de la connexion
            }
        })
    }
    fun fetchProductsByCategory(category: String) {
        apiService.getProductsByCategory(category.lowercase()).enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                if (response.isSuccessful) {
                    response.body()?.let { products ->
                        listProductsByCategory.value = products
                    }
                } else {
                    // Error de la llama a la API
                }
            }
            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                // Error de la connexion
            }
        })
    }
    fun insertCategory(category: List<CategoryModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(category)
    }
}