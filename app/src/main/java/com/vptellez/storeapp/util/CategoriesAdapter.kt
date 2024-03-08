package com.vptellez.storeapp.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.storeapp.R
import kotlin.math.log

/*class CategoriesAdapter(private val categories: List<String>) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {*/
class CategoriesAdapter(private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var context: Activity? = null
    private var categories: List<String> = mutableListOf()

    fun submitList(onCategories: List<String>) {
        categories = onCategories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false)
        return CategoryViewHolder(itemView)

        /*
        val listViewHolder = CategoryViewHolder(itemView)
        listViewHolder.listCategoryText.setOnClickListener { v ->
            val id = v.getTag()
            Log.i("STOREAPP: ","id: " + id)
            if (id != null) {
                /*val intent = Intent(context, NotificationDetailActivity::class.java)
                intent.putExtra(NotificationDetailActivity.extra_id_notification, id)
                context?.startActivityForResult(intent, 1)*/
            }
        }
        return listViewHolder*/
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTextView.text = category.uppercase()
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.category_title)

        init {
            itemView.setOnClickListener {
                val category = categoryTextView.text.toString()
                onCategoryClickListener.onCategoryClick(category)
            }
        }

        fun bind(category: String) {
            categoryTextView.text = category
        }
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(category: String)
    }
}

/*class CategoriesAdapter(private val categories: List<String>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false)
        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTextView.text = category.uppercase()
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.category_title)
    }
}*/