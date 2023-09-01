package com.example.newsappforandroid.product.constants.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

object LiveDataUtils {

    @JvmStatic
    fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
        this.observe(
            owner
        ) {
            it?.let(observer)
        }
    }
}





