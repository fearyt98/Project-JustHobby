package com.aurimteam.justhobby.RecoveryActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aurimteam.justhobby.R
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.TextView


class RecoveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        val recoveryView = findViewById<RelativeLayout>(R.id.recovery)
        recoveryView.setOnFocusChangeListener { view: View, b: Boolean ->
            if (b) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val cancel = findViewById<TextView>(R.id.recoveryCancel)
        cancel.setOnClickListener {
            finish()
        }
    }
}
