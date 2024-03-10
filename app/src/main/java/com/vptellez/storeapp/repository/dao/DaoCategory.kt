package com.vptellez.storeapp.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vptellez.storeapp.model.CategoryModel
@Dao
interface DaoCategory {
    @Query("SELECT * FROM categories")
    fun getCategories() : LiveData<List<CategoryModel>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryModel>)
    @Delete
    suspend fun deleteCategories(categories: CategoryModel)
    @Query("UPDATE categories set name = :name")
    suspend fun updateCategories(name: String?)
}