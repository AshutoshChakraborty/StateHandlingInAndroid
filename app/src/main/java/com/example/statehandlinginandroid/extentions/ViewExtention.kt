package com.example.statehandlinginandroid.extentions

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show() {
    this.visibility = View.VISIBLE
}
fun View.gone() {
    this.visibility = View.GONE
}

fun Context.showToast(msg:String = "Something went wrong") {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}