package com.example.bitcoinpricetracker.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bitcoinpricetracker.SUPPORTED_CURRENCIES
import com.example.bitcoinpricetracker.ui.BitcoinViewPagerPageFragment

class BitcoinViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return SUPPORTED_CURRENCIES.size
    }

    override fun createFragment(position: Int): Fragment {
        return BitcoinViewPagerPageFragment.newInstance(SUPPORTED_CURRENCIES[position])
    }
}