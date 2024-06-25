package com.github.mukiva.feature.airtickets.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.ItemHintBinding
import com.github.mukiva.feature.airtickets.databinding.ItemRecommendationBinding

class RecommendationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val mBinding: ItemRecommendationBinding

    init {
        val inflater = LayoutInflater.from(context)
        mBinding = ItemRecommendationBinding.inflate(inflater, this, true)

        context.theme
            .obtainStyledAttributes(attributeSet, R.styleable.RecommendationView, defStyleAttr, defStyleRes)
            .apply {
                try {
                    setTownName(getString(R.styleable.RecommendationView_townName) ?: "")
                    setImage(getResourceId(R.styleable.RecommendationView_image, 0))
                } finally {
                    recycle()
                }
            }

    }

    fun getTownName(): String {
        return mBinding.town.text.toString()
    }

    private fun setTownName(name: String) {
        mBinding.town.text = name
    }

    private fun setImage(@DrawableRes res: Int) {
        mBinding.image.setImageResource(res)
    }

}