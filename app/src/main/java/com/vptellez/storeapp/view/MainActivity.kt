package com.vptellez.storeapp.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.vptellez.storeapp.R
import com.vptellez.storeapp.util.CategoriesAdapter
import com.vptellez.storeapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, CategoriesAdapter.OnCategoryClickListener {

    private var drawer: DrawerLayout? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var productViewModel: MainViewModel
    private lateinit var adapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation)

        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.menu)

        drawer?.addDrawerListener(toggle)
        toggle.syncState()
        toggle.toolbarNavigationClickListener = View.OnClickListener { view: View? ->
            drawer?.openDrawer(
                GravityCompat.START
            )
        }

        productViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        productViewModel.fetchProductCategories()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recycler_view_categories)
        recyclerView.layoutManager = layoutManager

        observeProductCategories()
    }

    private fun observeProductCategories() {
        productViewModel.productCategories.observe(this, Observer { categories ->
            /*adapter = CategoriesAdapter(categories)*/
            adapter = CategoriesAdapter(this)
            adapter.submitList(categories)
            recyclerView.adapter = adapter
            /*adapter.submitList(categories)*/
        })
        /*productViewModel.fetchProductCategories()*/
    }

    override fun onCategoryClick(category: String) {
        Log.i("STOREAPP: ","category: " + category)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        /*when (item.itemId) {
            R.id.menu_item1 -> {
                // Acción cuando se selecciona el primer elemento del menú
                return true
            }
            R.id.menu_item2 -> {
                // Acción cuando se selecciona el segundo elemento del menú
                return true
            }
        }*/
        return false
    }
}