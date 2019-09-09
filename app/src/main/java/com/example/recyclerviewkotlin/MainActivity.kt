package com.example.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var rvDishList:RecyclerView? = null
    var adapter:AdapterCustom?= null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dishes = ArrayList<Dish>()

        dishes.add(Dish("Barbecue",300.00,4.00F,R.drawable.barbecue))
        dishes.add(Dish("Barbecue",300.00,4.00F,R.drawable.empanadas))
        dishes.add(Dish("Barbecue",300.00,4.00F,R.drawable.shawarma))
        dishes.add(Dish("Barbecue",300.00,4.00F,R.drawable.spaguetties))
        dishes.add(Dish("Barbecue",300.00,4.00F,R.drawable.gish))

        rvDishList = findViewById(R.id.rv_dish_list)
        rvDishList?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rvDishList?.layoutManager = layoutManager
        adapter = AdapterCustom(this,dishes)
        rvDishList?.adapter = adapter
    }
}
