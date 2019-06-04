package com.edicasoft.onbordingFeature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class OnbordingAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = ITEMS_COUNT

    override fun getItem(position: Int): Fragment = OnbordingFragment.newInstance(
        "title $position",
        "message $position"
    )

    private companion object {
        const val ITEMS_COUNT = 3
    }
}