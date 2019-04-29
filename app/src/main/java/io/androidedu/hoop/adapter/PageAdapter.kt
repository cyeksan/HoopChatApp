package io.androidedu.hoop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.androidedu.hoop.ui.fragment.CallFragment
import io.androidedu.hoop.ui.fragment.ChatFragment
import io.androidedu.hoop.ui.fragment.StatusFragment

class PageAdapter(
    fm: FragmentManager,
    private var numOfTabs: Int
) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {

            0 -> ChatFragment()
            1 -> StatusFragment()
            2 -> CallFragment()

            else -> null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {

            0 -> "CHATS"
            1 -> "STATUS"
            2 -> "CALLS"

            else -> null
        }
    }


}