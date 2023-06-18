package ir.misterdeveloper.mypostsapplication.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.errorSnack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length)
    snack.setActionTextColor(Color.parseColor("#FFFFFF"))
    snack.view.setBackgroundColor(Color.parseColor("#C62828"))
    snack.show()

}


fun Context.toast(context: Context, text:String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

