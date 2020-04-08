
package com.sbn.covid19.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sbn.covid19.databinding.CountryItemBinding
import com.sbn.covid19.shared.db.CovidCountry

class CountryAdapter(private val listener: OnItemSelected) :
    ListAdapter<CovidCountry, CountryViewHolder>(CovidCountryDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

}

class CountryViewHolder(
    private val binding: CountryItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CovidCountry, listener: OnItemSelected) {
        binding.apply {
            this.item = item
            this.listener = listener
            executePendingBindings()
        }
    }
}

object CovidCountryDiff : DiffUtil.ItemCallback<CovidCountry>() {
    override fun areItemsTheSame(oldItem: CovidCountry, newItem: CovidCountry): Boolean {
        return oldItem.country == newItem.country
    }

    override fun areContentsTheSame(oldItem: CovidCountry, newItem: CovidCountry) = oldItem == newItem
}
