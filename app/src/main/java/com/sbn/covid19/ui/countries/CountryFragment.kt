package com.sbn.covid19.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbn.covid19.R
import com.sbn.covid19.databinding.FragmentCountryBinding
import com.sbn.covid19.shared.result.EventObserver
import com.sbn.covid19.shared.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class CountryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private lateinit var viewModel: CountryViewModel
    private lateinit var binding: FragmentCountryBinding
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private val adapter by lazy {
        CountryAdapter(viewModel)
    }


    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentCountryBinding.inflate(inflater,container,false).apply {
            this.viewModel = this@CountryFragment.viewModel
            lifecycleOwner = this@CountryFragment
        }
        viewModel.remoteData()
        arguments?.let {bundle ->
            CountryFragmentArgs.fromBundle(bundle).let {
                viewModel.updateList(null,it.filter)
            }
        }
        return binding.root
    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            this.adapter = this@CountryFragment.adapter
        }

        binding.toolbar.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    dismissKeyboard(this@apply)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.updateList(newText)
                    return true
                }
            })

            // Set focus on the SearchView and open the keyboard
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    showKeyboard(view.findFocus())
                }
            }
            requestFocus()
        }

        viewModel.list.observe(viewLifecycleOwner, Observer { adapter.submitList(it)})
        viewModel.onItemSelected.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(CountryFragmentDirections.actionNavigationCountriesToCountryByDetailFragment(it))
        })

    }


    override fun onPause() {
        dismissKeyboard(binding.toolbar.searchView)
        super.onPause()
    }

    private fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    private fun dismissKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
