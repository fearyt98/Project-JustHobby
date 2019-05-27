package com.aurimteam.justhobby.FeaturesActivities

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class FeaturesAdapter(context: Context) : PagerAdapter() {

    private val context: Context = context
    private lateinit var layoutInflater: LayoutInflater
    //массив макетов активити
    private val layouts: IntArray = intArrayOf(
        R.layout.activity_feature_noty,
        R.layout.activity_feature_search,
        R.layout.activity_feature_courses
    )

    //получение числа активити
    override fun getCount(): Int = layouts.size

    //назначение View объектом
    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == (p1 as View)
    }

    //возвращение макета на заданной активити просмотра
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(layouts[position], container, false)
        container.addView(view)
        return view
    }

    //остановка слайда на последнем экране
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view: View = `object` as View
        container.removeView(view)
    }
}