package com.example.bitcoinpricetracker.ui.adapter

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.bitcoinpricetracker.SUPPORTED_CURRENCIES
import com.example.bitcoinpricetracker.ui.BitcoinViewPagerPageFragment
import com.example.bitcoinpricetracker.ui.MainViewModel

class BitcoinViewPagerAdapter(private val fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return SUPPORTED_CURRENCIES.size
    }

    override fun createFragment(position: Int): Fragment {
        return BitcoinViewPagerPageFragment.newInstance(SUPPORTED_CURRENCIES[position])
    }
}