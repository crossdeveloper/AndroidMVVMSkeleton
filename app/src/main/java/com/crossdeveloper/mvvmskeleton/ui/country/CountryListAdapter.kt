package com.crossdeveloper.mvvmskeleton.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.crossdeveloper.mvvmskeleton.R
import com.crossdeveloper.mvvmskeleton.data.model.CountryData

class CountryListAdapter(private val clickCountryListener: ClickCountryListener)
    :ListAdapter<CountryData, CountryListAdapter.CountryItemViewHolder> (COUNTRY_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder =
        CountryItemViewHolder(parent, R.layout.item_country_list)

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    inner class CountryItemViewHolder(
        parent: ViewGroup,
        @LayoutRes itemViewLayoutId: Int
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(itemViewLayoutId, parent, false)) {

        fun render(country: CountryData) {

        }
    }

    interface ClickCountryListener {
        fun onClickCountry(country: CountryData)
    }

    companion object {
        val COUNTRY_COMPARATOR = object : DiffUtil.ItemCallback<CountryData>() {
            override fun areContentsTheSame(oldItem: CountryData, newItem: CountryData): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: CountryData, newItem: CountryData): Boolean =
                oldItem.code == newItem.code
        }
    }
}