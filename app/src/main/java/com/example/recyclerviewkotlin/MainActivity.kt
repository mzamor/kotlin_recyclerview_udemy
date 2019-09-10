package com.example.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    var rvDishList:RecyclerView? = null
    var adapter:AdapterCustom?= null
    var layoutManager: RecyclerView.LayoutManager? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dishes = ArrayList<Dish>()

        dishes.add(Dish("Barbecue",300.00,4.00F,R.drawable.barbecue))
        dishes.add(Dish("Empanadas",300.00,4.00F,R.drawable.empanadas))
        dishes.add(Dish("Shawarma",300.00,4.00F,R.drawable.shawarma))
        dishes.add(Dish("Spaguetties",300.00,4.00F,R.drawable.spaguetties))
        dishes.add(Dish("Fish",300.00,4.00F,R.drawable.gish))

        rvDishList = findViewById(R.id.rv_dish_list)
        rvDishList?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rvDishList?.layoutManager = layoutManager
        adapter = AdapterCustom(this,dishes, object: ClickListener{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(applicationContext,dishes.get(position).name,Toast.LENGTH_LONG).show()
            }

        })
        rvDishList?.adapter = adapter
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout?.setOnRefreshListener {
            for(i in 1..10000){

            }
            swipeRefreshLayout?.isRefreshing=false
            dishes.add(Dish("Shawarma2",300.00,4.00F,R.drawable.shawarma2))
            adapter?.notifyDataSetChanged()
        }
    }
}
