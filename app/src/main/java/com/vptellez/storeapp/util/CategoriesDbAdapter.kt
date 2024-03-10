package com.vptellez.storeapp.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.storeapp.R
import com.vptellez.storeapp.model.CategoryModel
class CategoriesDbAdapter(private val onCategoryClickListener: CategoriesAdapter.OnCategoryClickListener) : RecyclerView.Adapter<CategoriesDbAdapter.CategoryDbViewHolder>() {
    private val todoCategories = ArrayList<CategoryModel>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<CategoryModel>){
        todoCategories.clear()
        todoCategories.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesDbAdapter.CategoryDbViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false)
        return CategoryDbViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: CategoriesDbAdapter.CategoryDbViewHolder, position: Int) {
        val category = todoCategories[position]
        holder.bind(category)
    }
    override fun getItemCount(): Int {
        return todoCategories.size
    }
    interface OnCategoryClickListener {
        fun onCategoryClick(category: String)
    }
    inner class CategoryDbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.category_title)
        init {
            itemView.setOnClickListener {
                val category = categoryTextView.text.toString()
                onCategoryClickListener.onCategoryClick(category)
            }
        }
        fun bind(category: CategoryModel) {
            categoryTextView.text = category.name.uppercase()
        }
    }
}