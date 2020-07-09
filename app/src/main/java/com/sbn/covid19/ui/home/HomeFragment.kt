package com.sbn.covid19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sbn.covid19.databinding.FragmentHomeBinding
import com.sbn.covid19.shared.result.EventObserver
import com.sbn.covid19.shared.util.Filter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.clickByCase.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationCountries(
                Filter.CASE))
        })

        viewModel.clickByDeaths.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationCountries(
                Filter.DEATH))
        })

        viewModel.clickByRecovered.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationCountries(
                Filter.RECOVERED))
        })
    }
}
