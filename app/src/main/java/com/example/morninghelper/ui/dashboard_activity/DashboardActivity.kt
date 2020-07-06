package com.example.morninghelper.ui.dashboard_activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.morninghelper.R
import com.example.morninghelper.tools.Tools.tabSelectedListener
import com.example.morninghelper.application.App
import com.example.morninghelper.tools.setColor
import com.example.morninghelper.ui.dashboard_activity.fragments.alarm_clock.AlarmClockFragment
import com.example.morninghelper.ui.dashboard_activity.fragments.habits_tracker.HabitsTrackerFragment
import com.example.morninghelper.ui.dashboard_activity.fragments.music.MusicFragment
import com.example.morninghelper.ui.dashboard_activity.fragments.news.NewsFragment
import com.example.morninghelper.view_pager_adapter.ViewPagerAdapter
import com.google.android.material.shape.MaterialShapeUtils.setElevation
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class DashboardActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var fragmentsItems = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        init()
    }

    private fun init() {
        fragmentsItems.add(AlarmClockFragment())
        fragmentsItems.add(HabitsTrackerFragment())
        fragmentsItems.add(NewsFragment())
        fragmentsItems.add(MusicFragment())
        viewPagerAdapter =
            ViewPagerAdapter(
                supportFragmentManager,
                fragmentsItems
            )
        viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.notifyDataSetChanged()
        addTabItem()
        attachToolbar()
    }


    private fun addTabItem() {

        tabLayout.addTab(
            tabLayout.newTab().setText(App.instance.getContext().getString(R.string.alarms))
        )
        tabLayout.addTab(
            tabLayout.newTab().setText(App.instance.getContext().getString(R.string.habits_tracker))
        )
        tabLayout.addTab(
            tabLayout.newTab().setText(App.instance.getContext().getString(R.string.news))
        )
        tabLayout.addTab(
            tabLayout.newTab().setText(App.instance.getContext().getString(R.string.music))
        )
        onTabClickedPageChangeListener()
    }


    private fun onTabClickedPageChangeListener() {
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.tabSelectedListener(viewPager)

   }

    private fun attachToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setElevation(toolBar, 0f)
        settingsBT.setBackgroundResource(R.mipmap.ic_settings_white)
        titleTV.setColor(
            getString(R.string.morning),
            ContextCompat.getColor(this, android.R.color.white)
        )
        titleTV.setColor(
            getString(R.string.helper),
            ContextCompat.getColor(this, R.color.yellowColor)
        )
    }

}