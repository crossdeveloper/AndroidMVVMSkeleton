package com.crossdeveloper.mvvmskeleton.ui.holiday

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crossdeveloper.mvvmskeleton.R
import com.crossdeveloper.mvvmskeleton.data.model.HolidayData

class HolidayListAdapter(private val onClickItemListener: OnClickItemListener)
    : ListAdapter<HolidayData, HolidayListAdapter.HolidayItemViewHolder>(HOLIDAY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayItemViewHolder =
        HolidayItemViewHolder(parent, R.layout.item_holiday_list)

    override fun onBindViewHolder(holder: HolidayItemViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    inner class HolidayItemViewHolder(
        parent: ViewGroup,
        @LayoutRes itemViewLayoutId: Int
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(itemViewLayoutId, parent, false)) {

        fun render(holiday: HolidayData) {
            itemView.findViewById<TextView>(R.id.holiday_nameTV).text = holiday.name
            itemView.setOnClickListener {
                onClickItemListener.onClickItem(holiday)
            }
        }
    }

    interface OnClickItemListener {
        fun onClickItem(holiday: HolidayData)
    }

    companion object {
        val HOLIDAY_COMPARATOR = object : DiffUtil.ItemCallback<HolidayData>() {
            override fun areContentsTheSame(oldItem: HolidayData, newItem: HolidayData): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: HolidayData, newItem: HolidayData): Boolean =
                oldItem.country == newItem.country
        }
    }
}