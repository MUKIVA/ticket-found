package com.github.mukiva.feature.airtickets.ui.all_tickets_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.FragmentAllTcketsBinding
import com.github.mukiva.feature.airtickets.presentation.TicketsOffersViewModel
import com.github.mukiva.ticketfound.uikit.component
import com.github.mukiva.ticketfound.uikit.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTicketsFragment : Fragment(R.layout.fragment_all_tckets) {

    private val mBinding by viewBindings(FragmentAllTcketsBinding::bind)
    private val mViewModel by viewModels<TicketsOffersViewModel>()

    private val mTicketListComponent by component {
        TicketListComponent(
            binding = mBinding.layAllTicketsList
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFloatingButtons()

        mTicketListComponent.initComponent()
        mTicketListComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)

        arguments?.let { args ->
            val from = args.getString(FROM_COUNTRY_KEY) ?: ""
            val to = args.getString(TO_COUNTRY_KEY) ?: ""
            val date = args.getString(START_DATE_KEY) ?: ""
            val personCount = args.getInt(PERSON_COUNT_KEY)

            initActionBar(
                from = from,
                to = to,
                personCount = personCount,
                date = date,
            )
        }

    }

    private fun initActionBar(
        from: String,
        to: String,
        personCount: Int,
        date: String
    ) = with(mBinding.layActionBar) {
        navigateBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
        transition.text = getString(R.string.template_range, from, to)
        val personCountString = requireContext().resources
            .getQuantityString(R.plurals.person, personCount, personCount)
        details.text = getString(R.string.template_range, date, personCountString)
    }

    private fun initFloatingButtons() = with(mBinding) {
        filterButton.setOnClickListener {
            //TODO()
        }
        graphicButton.setOnClickListener {
            //TODO()
        }
    }
    companion object {
        const val FROM_COUNTRY_KEY = "FROM_COUNTRY_KEY"
        const val TO_COUNTRY_KEY = "TO_COUNTRY_KEY"
        const val START_DATE_KEY = "START_DATE_KEY"
        const val PERSON_COUNT_KEY = "PERSON_COUNT_KEY"
    }
}