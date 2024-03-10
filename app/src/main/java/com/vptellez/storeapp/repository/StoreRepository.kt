package com.vptellez.storeapp.repository

import androidx.lifecycle.LiveData
import com.vptellez.storeapp.model.CategoryModel
import com.vptellez.storeapp.repository.dao.DaoCategory
class StoreRepository(private val categoryDao: DaoCategory) {
    val allCategories: LiveData<List<CategoryModel>> = categoryDao.getCategories()
    suspend fun insert(categoryModel: List<CategoryModel>) {
        categoryDao.insertCategories(categoryModel)
    }
    suspend fun delete(categoryModel: CategoryModel) {
        categoryDao.deleteCategories(categoryModel)
    }
    suspend fun update(categoryModel: CategoryModel) {
        categoryDao.updateCategories(categoryModel.name)
    }
}