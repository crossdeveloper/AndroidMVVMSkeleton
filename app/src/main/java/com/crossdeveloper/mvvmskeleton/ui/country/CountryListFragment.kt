package com.crossdeveloper.mvvmskeleton.ui.country

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crossdeveloper.mvvmskeleton.R
import com.crossdeveloper.mvvmskeleton.data.base.BaseActivity
import com.crossdeveloper.mvvmskeleton.data.base.BindingFragment
import com.crossdeveloper.mvvmskeleton.data.model.CountryData
import com.crossdeveloper.mvvmskeleton.databinding.FragmentCountryListBinding
import com.crossdeveloper.mvvmskeleton.extensions.toast
import com.crossdeveloper.mvvmskeleton.ui.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CountryListFragment : BindingFragment<FragmentCountryListBinding>(), CountryListAdapter.ClickCountryListener {

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_country_list

    private val adapter by lazy { CountryListAdapter(this)}
    private val viewModel: SharedViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
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

            viewModel.isLoading.observe(viewLifecycleOwner, { isSending ->
                if (isSending) {
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

    override fun onClickCountry(country: CountryData) {

    }
}

