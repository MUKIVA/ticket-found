package com.github.mukiva.feature.airtickets.ui.search_bottom_sheet

import com.github.mukiva.feature.airtickets.databinding.LayRecommendationListBinding
import com.github.mukiva.ticketfound.uikit.Component

internal class RecommendationsComponent(
    private val binding: LayRecommendationListBinding,
    private val onRecommendationClick: (String) -> Unit
) : Component() {

    override fun initComponent() = with(binding) {
        recommendation1.setOnClickListener {
            onRecommendationClick(recommendation1.getTownName())
        }
        recommendation2.setOnClickListener {
            onRecommendationClick(recommendation2.getTownName())
        }
        recommendation3.setOnClickListener {
            onRecommendationClick(recommendation3.getTownName())
        }
    }

}