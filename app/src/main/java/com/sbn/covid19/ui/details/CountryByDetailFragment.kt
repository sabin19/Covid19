package com.sbn.covid19.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sbn.covid19.databinding.FragmentCountryByDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class CountryByDetailFragment : Fragment() {

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private val viewModel: CountryByDetailsViewModel by viewModels()
    private lateinit var binding: FragmentCountryByDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryByDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.viewModel = this@CountryByDetailFragment.viewModel
            lifecycleOwner = this@CountryByDetailFragment
        }
        arguments?.let { bundle ->
            CountryByDetailFragmentArgs.fromBundle(bundle).let {
                viewModel.country(it.country.country)
            }
        }

    }

}
