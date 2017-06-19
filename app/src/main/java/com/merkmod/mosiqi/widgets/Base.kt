package com.merkmod.mosiqi.widgets

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.merkmod.mosiqi.R
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen
import com.wealthfront.magellan.ScreenGroup
import kotlinx.android.synthetic.main.base_tab_screen.view.*

/**
 * Created by rkodekar on 6/19/17.
 */
object Base {

    class BaseScreen(screenList: List<Screen<*>>): ScreenGroup<Screen<*>, BaseView>(screenList) {
        override fun createView(context: Context?) = BaseView(context!!, screens)

        override fun onShow(context: Context?) {
            super.onShow(context)
            screens.forEach {
                view.addTabView(it.getView())
            }
        }

        override fun onSave(outState: Bundle?) {
            super.onSave(outState)
        }

        override fun onRestore(savedInstanceState: Bundle?) {
            super.onRestore(savedInstanceState)
        }
    }

    class BaseView: BaseScreenView<BaseScreen> {

        constructor(context: Context) : super(context)

        constructor(context: Context, screenList: List<Screen<*>>): super(context) {
            View.inflate(context, R.layout.base_tab_screen, this)
            initPager(screenList)
        }

        fun initPager(screenList : List<Screen<*>>) {
            val adapter = TabsAdapter(screenList)
            pager.adapter = adapter
            pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
            tab_layout.setupWithViewPager(pager, true)
        }

        fun addTabView(view: View) {
            pager.addView(view)
        }

        inner class TabsAdapter(var screenList: List<Screen<*>>): PagerAdapter() {

            override fun instantiateItem(container: ViewGroup?, position: Int) = screenList[position].getView()!!

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {}

            override fun isViewFromObject(view: View?, `object`: Any?) = view == `object`

            override fun getCount() = screenList.size

            override fun getPageTitle(position: Int) = when(position) {
                0 -> "Songs"
                1 -> "Albums"
                2 -> "Artists"
                else -> "Songs"
            }
        }
    }
}