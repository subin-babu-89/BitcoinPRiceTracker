package com.example.bitcoinpricetracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bitcoinpricetracker.util.SUPPORTED_CURRENCIES
import com.example.bitcoinpricetracker.databinding.MainFragmentBinding
import com.example.bitcoinpricetracker.network.BitcoinTrackerService
import com.example.bitcoinpricetracker.ui.adapter.BitcoinViewPagerAdapter
import com.example.bitcoinpricetracker.util.SharedPrefUtils
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Fragment hosting the view pager used to display currency details
 */
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: BitcoinViewPagerAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            MainViewModel.Factory(BitcoinTrackerService.create())
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // bind timer with viewmodel initial time and start the chronometer
        binding.time.base = viewModel.initialTime
        binding.time.start()

        adapter = BitcoinViewPagerAdapter(this)

        viewModel.bitcoinTicker.observe(viewLifecycleOwner, {
            if (binding.bitcoinViewPager.adapter == null) {
                binding.bitcoinViewPager.adapter = adapter
                TabLayoutMediator(binding.tabs, binding.bitcoinViewPager) { tab, position ->
                    tab.text = SUPPORTED_CURRENCIES[position]
                }.attach()
                binding.bitcoinViewPager.currentItem = SharedPrefUtils.read(requireActivity())
            }
        })
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        SharedPrefUtils.write(requireActivity(), binding.bitcoinViewPager.currentItem)
    }
}