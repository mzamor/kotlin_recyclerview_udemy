package com.example.recyclerviewkotlin

class Dish(name:String, price:Double, rating:Float, picture:Int) {
    var name= ""
    var price= 0.0
    var rating = 0.0F
    var picture = 0
    init{
        this.name=name
        this.price=price
        this.rating=rating
        this.picture = picture
    }
}