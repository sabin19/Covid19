package com.sbn.covid19.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sbn.covid19.R
import com.sbn.covid19.databinding.FragmentInfoBinding
import com.sbn.covid19.shared.util.viewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InfoFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: InfoViewModel
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentInfoBinding.inflate(inflater,container,false).apply {
            this.viewModel = this@InfoFragment.viewModel
            lifecycleOwner = this@InfoFragment
        }
        return binding.root
    }
}
