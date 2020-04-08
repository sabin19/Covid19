package com.sbn.covid19.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sbn.covid19.databinding.FragmentCountryByDetailBinding
import com.sbn.covid19.shared.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CountryByDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private lateinit var viewModel: CountryByDetailsViewModel
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
        viewModel = viewModelProvider(viewModelFactory)
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
