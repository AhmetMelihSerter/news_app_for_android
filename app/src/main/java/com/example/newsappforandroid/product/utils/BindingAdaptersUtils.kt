package com.example.newsappforandroid.product.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class BindingAdaptersUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("endIconShow")
        fun TextInputEditText.setTextAndHideEndIconAgain(text: String) {
            this.setText(text)
            (this.parent.parent as TextInputLayout).isEndIconVisible = false
        }
    }
}
