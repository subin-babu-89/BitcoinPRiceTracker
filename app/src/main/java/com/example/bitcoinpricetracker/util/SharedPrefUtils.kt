package com.example.bitcoinpricetracker.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.bitcoinpricetracker.R

class SharedPrefUtils {
    companion object {
        fun write(activity: Activity, pageIdx: Int) {
            val sharedPref: SharedPreferences =
                activity.getPreferences(Context.MODE_PRIVATE) ?: return
            val edit: SharedPreferences.Editor = sharedPref.edit()
            edit.putInt(activity.resources.getString(R.string.last_viewed_page), pageIdx)
            edit.apply()
        }

        fun read(activity: Activity): Int {
            val defaultValue = activity.resources.getInteger(R.integer.default_viewpage_index)
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return defaultValue
            return sharedPref.getInt(
                activity.resources.getString(R.string.last_viewed_page),
                defaultValue
            )
        }
    }
}