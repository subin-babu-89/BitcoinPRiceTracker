package com.example.bitcoinpricetracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bitcoinpricetracker.databinding.BitcoinViewpagerPageFragmentBinding
import com.example.bitcoinpricetracker.network.BitcoinTrackerService

class BitcoinViewPagerPageFragment : Fragment() {
    companion object {
        const val ARG_OBJECT = "object"

        fun newInstance(btcValueStr: String): BitcoinViewPagerPageFragment {
            return BitcoinViewPagerPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_OBJECT, btcValueStr)
                }
            }
        }
    }

    private lateinit var binding: BitcoinViewpagerPageFragmentBinding
    private lateinit var btcValue: String

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            MainViewModel.Factory(BitcoinTrackerService.create())
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(ARG_OBJECT)) {
            btcValue = requireArguments().getString(ARG_OBJECT).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BitcoinViewpagerPageFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.bitcoinTicker.observe(viewLifecycleOwner, Observer {
            binding.textView.text = it.btcPrices()[btcValue]?.last.toString()
        })

        return binding.root
    }
}