package com.sbn.covid19.util

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.sbn.covid19.R


object ViewBindingAdapters {
    @JvmStatic
    @BindingAdapter("entries",requireAll = false)
    fun pieChartData(chart: PieChart, entries: List<PieEntry>?) {
        if(!entries.isNullOrEmpty()) {
            val pieData = PieDataSet(entries, "")
            pieData.setColors(ContextCompat.getColor(chart.context,R.color.android_navy),ContextCompat.getColor(chart.context,R.color.android_orange),ContextCompat.getColor(chart.context,R.color.color_red),ContextCompat.getColor(chart.context,R.color.android_green_dark))
            chart.animateY(3000)
            pieData.setDrawValues(true)
            val data = PieData(pieData)
            data.setValueFormatter(PercentFormatter(chart))
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            chart.data = data
            chart.legend.isEnabled = false
            chart.description.isEnabled = false
            chart.setUsePercentValues(true)
            chart.setDrawEntryLabels(false)
            chart.highlightValues(null)
        }
    }

}