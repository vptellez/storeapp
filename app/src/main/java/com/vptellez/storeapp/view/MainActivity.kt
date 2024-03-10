package com.vptellez.storeapp.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.vptellez.storeapp.R
import com.vptellez.storeapp.util.CategoriesAdapter
import com.vptellez.storeapp.util.CategoriesDbAdapter
import com.vptellez.storeapp.viewmodel.MainViewModel
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, CategoriesAdapter.OnCategoryClickListener {
    private var drawer: DrawerLayout? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var productViewModel: MainViewModel
    private lateinit var adapter: CategoriesAdapter
    private lateinit var adapterDb: CategoriesDbAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setNavigationItemSelectedListener(this)

        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.menu)

        drawer?.addDrawerListener(toggle)
        toggle.syncState()
        toggle.toolbarNavigationClickListener = View.OnClickListener { drawer?.openDrawer(GravityCompat.START) }

        productViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recycler_view_categories)
        recyclerView.layoutManager = layoutManager

        if(isInternetAvailable(this)) {
            productViewModel.fetchProductCategories()
            observeCategories()
        } else {
            getAllCategoriesDb()
        }
    }
    private fun observeCategories() {
        productViewModel.productCategories.observe(this) { categories ->
            adapter = CategoriesAdapter(this)
            adapter.submitList(categories)
            recyclerView.adapter = adapter
        }
    }
    private fun getAllCategoriesDb() {
        productViewModel.allCategories.observe(this) { list ->
            list?.let {
                adapterDb = CategoriesDbAdapter(this)
                adapterDb.updateList(list)
                recyclerView.adapter = adapterDb
            }
        }
    }
    override fun onCategoryClick(category: String) {
        if(category.isNotEmpty() && isInternetAvailable(this)) {
            val intent = Intent(this, ListProductsActivity::class.java)
            intent.putExtra(ListProductsActivity.extra_item_product, category)
            this.startActivity(intent)
        } else {
            Toast.makeText(this, R.string.toast_error_products, Toast.LENGTH_SHORT).show()
        }
    }
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_specification -> {
                startActivity(Intent(this, SpecificationActivity::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}