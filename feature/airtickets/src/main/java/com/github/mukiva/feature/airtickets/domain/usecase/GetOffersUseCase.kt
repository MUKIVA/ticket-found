package com.github.mukiva.feature.airtickets.domain.usecase

import com.github.mukiva.feature.airtickets.domain.Offer
import com.github.mukiva.ticketfound.data.IOffersRepository
import com.github.mukiva.ticketfound.data.utils.RequestResult
import com.github.mukiva.ticketfound.data.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.github.mukiva.ticketfound.data.models.Offer as DataOffer

class GetOffersUseCase @Inject constructor(
    private val offersRepository: IOffersRepository
) {

    operator fun invoke(): Flow<RequestResult<List<Offer>>> {
        return offersRepository.getOffers()
            .map { requestResult ->
                requestResult.map { offers ->
                    offers.map(::asDomainOffer)
                }
            }
    }

    private fun asDomainOffer(data: DataOffer): Offer {
        return Offer(
            id = data.id,
            title = data.title,
            town = data.town,
            price = data.price.value,
        )
    }
}