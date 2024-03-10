package com.vptellez.storeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "categories")
data class CategoryModel(
    @PrimaryKey val name: String
)