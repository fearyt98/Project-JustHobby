package com.aurimteam.justhobby.FeaturesActivities

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_intro_main.*

class IntroActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var introAdapter: IntroAdapter
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_main)

        preferenceManager = PreferenceManager(this)
        if (!preferenceManager.isFirstLaunch())
            launchMainScreen()

        viewPager = findViewById(R.id.introViewPager)
        dotsLayout = findViewById(R.id.introLayoutDots)
        setClickListener()
        setupViewPager()
        addDotsIndicator(0)
    }

    override fun onResume() {
        super.onResume()
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                addDotsIndicator(position)
                if (position == introAdapter.count - 1) {
                    toggleVisibilityView(introSkip, false)
                    toggleVisibilityView(introComplete, true)
                } else {
                    toggleVisibilityView(introSkip, true)
                    toggleVisibilityView(introComplete, false)
                }
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageScrollStateChanged(p0: Int) {}
        })
    }

    private fun toggleVisibilityView(view: View, is_visible: Boolean) {
        if(is_visible) {
            view.visibility = View.VISIBLE
            view.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        } else {
            view.visibility = View.INVISIBLE
            view.layoutParams.height = 0
        }
    }

    private fun launchMainScreen() {
        preferenceManager.setIsFirstLaunch(false)
        finish()
    }

    private fun setupViewPager() {
        introAdapter = IntroAdapter(this)
        viewPager.adapter = introAdapter
    }

    private fun setClickListener() {
        val textView: TextView = findViewById(R.id.introSkip)
        textView.setOnClickListener { skipIntro() }
    }

    private fun addDotsIndicator(position: Int) {
        val mDots: MutableList<TextView> = MutableList(introAdapter.count) { TextView(this) }
        dotsLayout.removeAllViews()
        for (i in 0 until introAdapter.count) {
            mDots[i].text = Html.fromHtml("•")
            mDots[i].textSize = 35f
            mDots[i].setTextColor(ContextCompat.getColor(this, R.color.whiteMiddle))
            dotsLayout.addView(mDots[i])
        }
        if (mDots.size > 0)
            mDots[position].setTextColor(ContextCompat.getColor(this, R.color.whiteTop))
    }

    private fun skipIntro() {
        /*val intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)*/
    }
}


