package com.example.recyclerviewkotlin

import android.view.View

interface LongClickListener {
    fun longClick(viev: View, position:Int)
}