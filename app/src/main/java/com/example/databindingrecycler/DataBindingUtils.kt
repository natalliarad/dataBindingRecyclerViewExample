package com.example.databindingrecycler

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Data binding does not provide a default way to display an image from an ID. So, a
 * BindingAdapter takes a reference to the object from which it was invoked, along with a
 * parameter to call setImageResource on the imageView that displays the image of the cat.
 */
@BindingAdapter("android:src")
fun setImageResourceToView(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}
