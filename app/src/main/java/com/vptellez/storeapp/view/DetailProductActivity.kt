package com.vptellez.storeapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vptellez.storeapp.R
import java.util.Objects
class DetailProductActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        val toolbar: Toolbar = findViewById(R.id.toolbar_detail)
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val textTitle: TextView = findViewById(R.id.detail_title)
        val textDescription: TextView = findViewById(R.id.detail_description)
        val textPrice: TextView = findViewById(R.id.detail_price)
        val textCategory: TextView = findViewById(R.id.detail_category)
        val textRating: TextView = findViewById(R.id.detail_rating)
        val imageIcon: ImageView = findViewById(R.id.detail_icon)

        val bundle = intent.extras
        if (bundle != null) {
            textTitle.text = bundle.getString("title")
            textDescription.text = bundle.getString("description")
            textCategory.text = "Category: " + bundle.getString("category")
            textPrice.text = "$" + bundle.getDouble("price").toString()
            textRating.text = "⭐" + bundle.getDouble("rate").toString()

            val imageUrl = bundle.getString("image")
            Glide.with(this)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                )
                .into(imageIcon)
        }
    }
}