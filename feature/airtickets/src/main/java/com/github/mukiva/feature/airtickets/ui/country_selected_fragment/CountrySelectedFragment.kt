package com.github.mukiva.feature.airtickets.ui.country_selected_fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.FragmentCountrySelectedBinding
import com.github.mukiva.feature.airtickets.presentation.SelectedCountryViewModel
import com.github.mukiva.feature.airtickets.ui.all_tickets_fragment.AllTicketsFragment
import com.github.mukiva.ticketfound.uikit.component
import com.github.mukiva.ticketfound.uikit.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter
import java.util.Calendar

@AndroidEntryPoint
class CountrySelectedFragment : Fragment(R.layout.fragment_country_selected) {

    private val mBinding by viewBindings(FragmentCountrySelectedBinding::bind)
    private val mViewModel by viewModels<SelectedCountryViewModel>()
    private val mCalendar = Calendar.getInstance()
    private val mLocalDateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM")

    private val mStartDatePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            mViewModel.updateStartDate(year, month, dayOfMonth)
        }

    private val mEndDatePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            mViewModel.updateEndDate(year, month, dayOfMonth)
        }

    private val mActionBarComponent by component {
        ActionBarComponent(
            binding = mBinding.laySelectedBar,
            onBackButton = ::navigateUp,
            onSwapButton = mViewModel::swapCountry,
            onClearButton = ::navigateUp,
        )
    }

    private val mConfigurationComponent by component {
        ConfigurationComponent(
            binding = mBinding.layConfiguration,
            onEndDateClick = { showDatePickerDialog(mEndDatePickerListener) },
            onStartDateClick = { showDatePickerDialog(mStartDatePickerListener) },
            onFilterClick = { /* TODO */ },
            onClassClick = { /* TODO */ },
        )
    }

    private val mTicketListComponent by component {
        TicketListComponent(
            binding = mBinding.layTicketRecommendation
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val from = args.getString(FROM_COUNTRY_KEY) ?: ""
            mViewModel.updateFromCountryName(from)
            val to = args.getString(TO_COUNTRY_KEY) ?: ""
            mViewModel.updateToCountryName(to)
        }

        mActionBarComponent.initComponent()
        mActionBarComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)

        mTicketListComponent.initComponent()
        mTicketListComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)

        mConfigurationComponent.initComponent()
        mConfigurationComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)

        mBinding.viewAllTicketsButton.setOnClickListener {
            with (AllTicketsFragment) {
                val selectedCountryState = mViewModel.selectedCountryState.value
                val configurationState = mViewModel.configurationState.value

                val args = bundleOf(
                    FROM_COUNTRY_KEY to selectedCountryState.from,
                    TO_COUNTRY_KEY to selectedCountryState.to,
                    START_DATE_KEY to mLocalDateTimeFormatter.format(configurationState.startDate),
                    PERSON_COUNT_KEY to configurationState.personCount
                )

                findNavController().navigate(R.id.allTicketsFragment, args)
            }
        }
    }

    private fun showDatePickerDialog(listener: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(requireContext(), listener,
            mCalendar.get(Calendar.YEAR),
            mCalendar.get(Calendar.MONTH),
            mCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    companion object {
        const val FROM_COUNTRY_KEY = "FROM_COUNTRY_KEY"
        const val TO_COUNTRY_KEY = "TO_COUNTRY_KEY"
    }

}