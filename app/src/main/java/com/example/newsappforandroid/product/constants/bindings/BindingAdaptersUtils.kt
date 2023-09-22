package com.example.newsappforandroid.product.constants.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.newsappforandroid.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object BindingAdaptersUtils {
    @JvmStatic
    @BindingAdapter("endIconShow")
    fun setTextAndHideEndIconAgain(textInputEditText: TextInputEditText, text: String) {
        textInputEditText.setText(text)
        (textInputEditText.parent.parent as TextInputLayout).isEndIconVisible = false
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(imageView)
                .load(url)
                .error(R.drawable.ic_image_not_found_24)
                .override(Target.SIZE_ORIGINAL)
                .into(imageView)
        }
    }
}
