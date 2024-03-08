package com.vptellez.storeapp.util

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.storeapp.R

class CategoriesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var listItem: LinearLayout
    var listCategoryText: TextView

    init {
        listItem = itemView.findViewById(R.id.list_item)
        listCategoryText = itemView.findViewById(R.id.category_title)
    }
}