package com.sbn.covid19.ui.countries

import com.sbn.covid19.shared.db.CovidCountry

interface OnItemSelected {
    fun onSelect(item: CovidCountry)
}