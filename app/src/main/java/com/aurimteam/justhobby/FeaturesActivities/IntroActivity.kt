package com.aurimteam.justhobby.FeaturesActivities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.activity_intro_main.*

class IntroActivity : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var dotsLayout: LinearLayout? = null
    private lateinit var introAdapter: IntroAdapter
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager = findViewById(R.id.view_pager_intro)
        dotsLayout = findViewById(R.id.layout_dots)

        preferenceManager = PreferenceManager(this)
        if (!preferenceManager.isFirstLaunch()) {
            launchMainScreen()
        }
        setContentView(R.layout.activity_intro_main)
        setClickListener()
        setupViewPager()
        addDotsIndicator(0)
    }

    override fun onResume() {
        super.onResume()
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                addDotsIndicator(position)
                if (position == introAdapter.count-1) intro_btn.text = "Пропустить"
                else intro_btn.text = "Завершить"
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageScrollStateChanged(p0: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun launchMainScreen() {
        preferenceManager.setIsFirstLaunch(false)
        finish()
    }

    private fun setupViewPager() {
        introAdapter = IntroAdapter(this)
        viewPager?.adapter = introAdapter
    }

    private fun setClickListener() {
        val textView: TextView = findViewById(R.id.intro_btn)
        textView.setOnClickListener { skipIntro() }
    }

    private fun addDotsIndicator(position: Int) {
        val mDots: MutableList<TextView> = MutableList(introAdapter.count) { TextView(this) }
        dotsLayout?.removeAllViews()
        for (i in 0..introAdapter.count-1) {
            mDots[i].setText(Html.fromHtml("•"))
            mDots[i].setTextSize(35f)
            mDots[i].setTextColor(getResources().getColor(R.color.dot_inactive_color))
            dotsLayout?.addView(mDots[i])
        }
        if (mDots.size > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.dot_active_color))
        }
    }

    private fun skipIntro() {
        //val intent = Intent(this, RecoveryActivity::class.java)
        //startActivity(intent)
    }
}


