package com.crossdeveloper.mvvmskeleton.ui.holiday

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.crossdeveloper.mvvmskeleton.R
import com.crossdeveloper.mvvmskeleton.data.base.BaseActivity
import com.crossdeveloper.mvvmskeleton.data.base.BindingFragment
import com.crossdeveloper.mvvmskeleton.data.model.HolidayData
import com.crossdeveloper.mvvmskeleton.databinding.FragmentHolidayListBinding
import com.crossdeveloper.mvvmskeleton.extensions.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HolidayListFragment:BindingFragment<FragmentHolidayListBinding>(), HolidayListAdapter.OnClickItemListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_holiday_list
    private val adapter by lazy { HolidayListAdapter(this) }

    val args: HolidayListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this

        initListeners()
        observers()
    }

    private fun initListeners() {
        binding.holidayList.layoutManager = LinearLayoutManager(activity)
        binding.holidayList.adapter = adapter
    }

    private fun observers() {
        binding.viewModel?.loadHolidays(args.countryCode ?: "")
        binding.viewModel?.let { viewModel->
            viewModel.holidays.observe(viewLifecycleOwner, {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            })

            viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
                if (isLoading) {
                    (activity as BaseActivity).showHUD()
                } else {
                    (activity as BaseActivity).dismissHUD()
                }
            })

            viewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
                errorMessage?.let { error ->
                    if (error.isNotEmpty()) {
                        toast(error)
                    }
                }
            })
        }
    }

    override fun onClickItem(holiday: HolidayData) {

    }

}