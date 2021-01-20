package com.crossdeveloper.mvvmskeleton.ui.country

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crossdeveloper.mvvmskeleton.R
import com.crossdeveloper.mvvmskeleton.data.base.BaseActivity
import com.crossdeveloper.mvvmskeleton.data.base.BindingFragment
import com.crossdeveloper.mvvmskeleton.data.model.CountryData
import com.crossdeveloper.mvvmskeleton.databinding.FragmentCountryListBinding
import com.crossdeveloper.mvvmskeleton.extensions.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CountryListFragment : BindingFragment<FragmentCountryListBinding>(), CountryListAdapter.OnClickItemListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_country_list

    private val adapter by lazy { CountryListAdapter(this)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this

        initListeners()
        observers()
    }

    private fun initListeners() {
        binding.countryList.layoutManager = LinearLayoutManager(activity)
        binding.countryList.adapter = adapter
    }

    private fun observers() {
        binding.viewModel?.loadCountries()
        binding.viewModel?.let { viewModel->
            viewModel.countries.observe(viewLifecycleOwner, {
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

    override fun onClickItem(country: CountryData) {
        country.code?.let {
            val action = CountryListFragmentDirections.startHolidayList(it)
            findNavController().navigate(action)
        }
   }
}

