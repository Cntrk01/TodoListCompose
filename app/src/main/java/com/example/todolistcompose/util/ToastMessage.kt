package com.example.todolistcompose.util

import android.content.Context
import android.widget.Toast

fun toastMsg(
    context: Context,
    message:String,
){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}