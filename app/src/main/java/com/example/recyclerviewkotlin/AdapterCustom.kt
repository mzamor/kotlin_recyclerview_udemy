package com.example.recyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCustom(var context: Context,dishes:ArrayList<Dish>, var listener:ClickListener, var longListener:LongClickListener):RecyclerView.Adapter<AdapterCustom.ViewHolder>() {
    var dishes:ArrayList<Dish>? = null

    init{
        this.dishes = dishes
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): AdapterCustom.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.template_dish,parent,false)
        val viewHolder = ViewHolder(view,listener, longListener)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return dishes?.count()!!
    }

    override fun onBindViewHolder(holder: AdapterCustom.ViewHolder, position: Int) {
        val dish = dishes?.get(position)
        holder.ivDish?.setImageResource(dish?.picture!!)
        holder.tvDishName?.setText(dish?.name)
        holder.tvDishPrice?.setText("$ "+dish?.price)
        holder.rbDishStars?.rating = dish?.rating!!
    }

    class ViewHolder(view:View, listener: ClickListener, longListener: LongClickListener):RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        var view = view
        var ivDish:ImageView? = null
        var tvDishName:TextView? = null
        var tvDishPrice:TextView? = null
        var rbDishStars:RatingBar? = null
        var listener:ClickListener?=null
        var longListener:LongClickListener?=null

        init {
            ivDish = view.findViewById<ImageView>(R.id.iv_dish)
            tvDishName = view.findViewById<TextView>(R.id.tv_dish_name)
            tvDishPrice = view.findViewById<TextView>(R.id.tv_dish_price)
            rbDishStars = view.findViewById<RatingBar>(R.id.rb_dish_stars)
            this.listener = listener
            this.view.setOnClickListener(this)
            this.longListener = longListener
            this.view.setOnLongClickListener(this)
        }
        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!,adapterPosition)
            return true
        }

    }
}