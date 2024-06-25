package com.github.mukiva.feature.airtickets.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.FragmentAnywhereBinding
import com.github.mukiva.ticketfound.uikit.viewBindings

class AnywhereFragment : Fragment(R.layout.fragment_anywhere) {

    private val mBinding by viewBindings(FragmentAnywhereBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}