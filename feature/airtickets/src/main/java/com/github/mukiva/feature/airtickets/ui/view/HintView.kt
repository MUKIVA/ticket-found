package com.github.mukiva.feature.airtickets.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.ItemHintBinding

class HintView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val mBinding: ItemHintBinding

    init {
        val inflater = LayoutInflater.from(context)
        mBinding = ItemHintBinding.inflate(inflater, this, true)

        context.theme
            .obtainStyledAttributes(attributeSet, R.styleable.HintView, defStyleAttr, defStyleRes)
            .apply {
                try {
                    setHintText(getString(R.styleable.HintView_hintText).toString())
                    setBackgroundTint(getColor(R.styleable.HintView_hintBackgroundTint, Color.BLACK))
                    setIcon(getResourceId(R.styleable.HintView_hintIcon, 0))
                } finally {
                    recycle()
                }
            }

    }

    private fun setBackgroundTint(@ColorInt color: Int) {
        mBinding.icon.backgroundTintList = ColorStateList.valueOf(color)
    }

    private fun setHintText(text: String) {
        mBinding.hintText.text = text
    }

    private fun setIcon(@DrawableRes res: Int) {
        mBinding.icon.setImageResource(res)
    }

}