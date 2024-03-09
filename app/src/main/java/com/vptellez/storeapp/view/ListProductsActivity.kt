package com.vptellez.storeapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.storeapp.R
import com.vptellez.storeapp.model.Products
import com.vptellez.storeapp.util.ProductsViewAdapter
import com.vptellez.storeapp.viewmodel.MainViewModel
import java.util.Objects

class ListProductsActivity : AppCompatActivity(), ProductsViewAdapter.OnProductClickListener {
    private var category: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var productViewModel: MainViewModel
    private lateinit var adapter: ProductsViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_products)

        val toolbar: Toolbar = findViewById(R.id.toolbar_products)
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val toolbarTitle: TextView = findViewById(R.id.toolbar_title_products)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recycler_view_products)
        recyclerView.layoutManager = layoutManager

        val intent = intent
        if (intent != null) {
            category = intent.getStringExtra(extra_item_product)
            if (category != null) {
                toolbarTitle.text = category
                productViewModel = ViewModelProvider(this)[MainViewModel::class.java]
                productViewModel.fetchProductsByCategory(category!!)
                observeProducts()
            } else {
                loadingError()
            }
        } else {
            loadingError()
        }
    }
    private fun observeProducts() {
        productViewModel.listProductsByCategory.observe(this) { products ->
            adapter = ProductsViewAdapter(this)
            adapter.submitList(products)
            recyclerView.adapter = adapter
        }
    }
    override fun onProductClick(product: Products) {
        val bundle = Bundle()
        bundle.putString("title", product.title)
        bundle.putString("description", product.description)
        bundle.putString("category", product.category)
        bundle.putString("image", product.image)
        bundle.putDouble("price", product.price)
        bundle.putDouble("rate", product.rating.rate)

        val intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    companion object {
        var extra_item_product: String? = ""
    }
    private fun loadingError() {
        Toast.makeText(this, R.string.toast_error_products, Toast.LENGTH_SHORT).show()
        finish()
    }
}