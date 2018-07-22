package com.example.godtfoodapp.utils

import android.content.Context
import android.content.ContextWrapper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}