package com.vptellez.storeapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.storeapp.R
class CategoriesAdapter(private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categories: List<String> = mutableListOf()
    fun submitList(onCategories: List<String>) {
        categories = onCategories
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false)
        return CategoryViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        /*holder.categoryTextView.text = category.uppercase()*/
        holder.bind(category)
    }
    override fun getItemCount(): Int {
        return categories.size
    }
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.category_title)
        init {
            itemView.setOnClickListener {
                val category = categoryTextView.text.toString()
                onCategoryClickListener.onCategoryClick(category)
            }
        }
        fun bind(category: String) {
            categoryTextView.text = category.uppercase()
        }
    }
    interface OnCategoryClickListener {
        fun onCategoryClick(category: String)
    }
}