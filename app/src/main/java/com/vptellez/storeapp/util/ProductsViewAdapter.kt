package com.vptellez.storeapp.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.storeapp.R
import com.vptellez.storeapp.model.Products

class ProductsViewAdapter(private val onProductClickListener: OnProductClickListener) : RecyclerView.Adapter<ProductsViewAdapter.ProductsViewHolder>() {
    private var products: List<Products> = mutableListOf()
    fun submitList(onProducts: List<Products>) {
        products = onProducts
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_products, parent, false)
        return ProductsViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }
    override fun getItemCount(): Int {
        return products.size
    }
    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemLineal: LinearLayout = itemView.findViewById(R.id.list_product_item)
        private val titleTextView: TextView = itemView.findViewById(R.id.list_product_title)
        private val priceTextView: TextView = itemView.findViewById(R.id.list_product_price)
        private val ratingTextView: TextView = itemView.findViewById(R.id.list_product_rating)
        private lateinit var itemProduct: Products
        init {
            itemLineal.setOnClickListener {
                /*val products = titleTextView.text.toString()*/
                onProductClickListener.onProductClick(itemProduct)
            }
        }
        @SuppressLint("SetTextI18n")
        fun bind(product: Products) {
            itemProduct = product
            titleTextView.text = product.title
            priceTextView.text = "$" + product.price.toString()
            ratingTextView.text = "⭐" + product.rating.rate.toString()
        }
    }
    interface OnProductClickListener {
        fun onProductClick(product: Products)
    }
}