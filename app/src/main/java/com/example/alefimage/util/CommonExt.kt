package com.example.alefimage.util

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun Int.toDp(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
).toInt()

fun ImageView.load(url: String, error: Int? = null) {
    if (error == null)
        Picasso.get().load(url).into(this)
    else Picasso.get().load(url).error(error).into(this)
}