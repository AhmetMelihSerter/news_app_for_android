package com.example.newsappforandroid.product.constants.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsappforandroid.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.orhanobut.logger.Logger

object BindingAdaptersUtils {
    @JvmStatic
    @BindingAdapter("endIconShow")
    fun TextInputEditText.setTextAndHideEndIconAgain(text: String) {
        this.setText(text)
        (this.parent.parent as TextInputLayout).isEndIconVisible = false
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Logger.d("Url: $url")
        if (!url.isNullOrEmpty()) {
            Glide.with(imageView)
                .load(url)
                .error(R.drawable.image_not_found)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }
}
