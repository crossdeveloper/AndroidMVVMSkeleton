package com.crossdeveloper.mvvmskeleton.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

//Toast
fun Context.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun Context.toast(@StringRes textRes: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, textRes, length).show()
}

fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    this.activity?.toast(text, length)
}

fun Fragment.toast(@StringRes textRes: Int, length: Int = Toast.LENGTH_SHORT) {
    this.activity?.toast(textRes, length)
}
