package com.github.mukiva.feature.airtickets.ui

import androidx.annotation.DrawableRes
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.ticketfound.uikit.R as UiKitRes
import com.github.mukiva.feature.airtickets.databinding.ItemOfferBinding
import com.github.mukiva.feature.airtickets.domain.Offer
import com.github.mukiva.ticketfound.uikit.formatAsCurrency
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@DrawableRes
internal fun idAsMockImageRes(id: Int): Int {
    return  when (id) {
        1 -> R.drawable.mock_offer_image_1
        2 -> R.drawable.mock_offer_image_2
        else -> R.drawable.mock_offer_image_3
    }
}

object AirTicketsAdapterDelegates {
    val offerAdapterDelegate = adapterDelegateViewBinding<Offer, Any, ItemOfferBinding>(
        { inflater, root -> ItemOfferBinding.inflate(inflater, root, false) }
    ) {
        bind {
            binding.image.setImageResource(idAsMockImageRes(item.id))
            binding.offerTitle.text = item.title
            binding.town.text = item.town
            binding.price.text =
                getString(UiKitRes.string.rub_template, item.price.formatAsCurrency())
        }
    }
}