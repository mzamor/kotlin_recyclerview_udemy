package com.example.recyclerviewkotlin

import android.view.View

interface ClickListener {
    fun onClick(view: View, position:Int)
}