package io.androidedu.hoop.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import io.androidedu.hoop.R
import io.androidedu.hoop.adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , TabLayout.OnTabSelectedListener{
    override fun onTabReselected(p0: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Hoop"
        setSupportActionBar(toolbar)

        val pageAdapter =
            PageAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pageAdapter

        tabLayout.setupWithViewPager(viewPager)

    }

}

