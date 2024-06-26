package com.github.mukiva.feature.airtickets.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.ItemFlightCardBinding
import com.github.mukiva.ticketfound.uikit.R as UiKitRes
import com.github.mukiva.feature.airtickets.databinding.ItemOfferBinding
import com.github.mukiva.feature.airtickets.databinding.ItemTicketBinding
import com.github.mukiva.feature.airtickets.domain.Offer
import com.github.mukiva.feature.airtickets.domain.Ticket
import com.github.mukiva.feature.airtickets.domain.TicketOffers
import com.github.mukiva.ticketfound.uikit.formatAsCurrency
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.time.format.DateTimeFormatter

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

    val ticketAdapterDelegate = adapterDelegateViewBinding<Ticket, Any, ItemTicketBinding>(
        { inflater, root -> ItemTicketBinding.inflate(inflater, root, false) }
    ) {
        bind {
            binding.companyName.text = item.companyName
            binding.timeRange.text = item.timeRange.joinToString(separator = " ")
        }
    }

    val ticketOffersAdapterDelegate = adapterDelegateViewBinding<TicketOffers, Any, ItemFlightCardBinding>(
        { inflater, root -> ItemFlightCardBinding.inflate(inflater, root, false) }
    ) {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        bind {

            val departureTime = timeFormatter.format(item.departureDate)
            val arrivalTime = timeFormatter.format(item.arrivalDate)
            val travelTimeRes = when (item.hasTransfer) {
                true -> R.string.template_travel_time
                false -> R.string.template_no_transfer_travel_time
            }

            val flightTime = item.flightTime
                .millisecondsAsHours()

            binding.price.text = getString(UiKitRes.string.rub_template, item.price.formatAsCurrency())
            binding.badge.isVisible = !item.badgeInfo.isNullOrBlank()
            binding.badge.text = item.badgeInfo
            binding.timeRange.text = getString(R.string.template_range, departureTime, arrivalTime)
            binding.travelTime.text = getString(travelTimeRes, flightTime)
            binding.airportInfo.text =
                getString(R.string.template_airport_info, item.departureAirportCode, item.arrivalAirportCode)
        }
    }
}

@SuppressLint("DefaultLocale")
internal fun Long.millisecondsAsHours(): String {
    return String.format("%.1f", (this / 3600000f))
}