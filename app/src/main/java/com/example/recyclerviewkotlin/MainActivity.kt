package com.example.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    var isActionMode= false
    var rvDishList:RecyclerView? = null
    var adapter:AdapterCustom?= null
    var layoutManager: RecyclerView.LayoutManager? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var actionMode:ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callBack = object: ActionMode.Callback{
            override fun onActionItemClicked(mode: ActionMode?, p1: MenuItem?): Boolean {
                adapter?.finishActionMode()
                mode?.finish()
                isActionMode=false
                return true
            }

            override fun onCreateActionMode(mode: ActionMode?, p1: Menu?): Boolean {
                adapter?.initActionMode()
                actionMode = mode
                isActionMode=true
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, p1: Menu?): Boolean {
                mode?.title = "Objetos Seleccionados"
                return false
            }

            override fun onDestroyActionMode(p0: ActionMode?) {
                adapter?.destroyActionMode()
                isActionMode=false
            }
        }



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
                if(isActionMode){
                    adapter?.selectDish(position)
                }
                actionMode?.title = adapter?.itemsSelected().toString() + " elementos seleccionados"

                if(adapter?.itemsSelected()==0){
                    actionMode?.finish()
                }

            }

        }, object:LongClickListener{
            override fun longClick(viev: View, position: Int) {
                if(!isActionMode){
                    startActionMode(callBack)
                    adapter?.selectDish(position)
                    isActionMode=true
                }

                if(adapter?.itemsSelected()==0){
                    actionMode?.finish()
                }
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
