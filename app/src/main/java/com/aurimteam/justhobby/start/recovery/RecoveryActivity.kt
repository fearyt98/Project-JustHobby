package com.aurimteam.justhobby.start.recovery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aurimteam.justhobby.R
import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.aurimteam.justhobby.start.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_recovery.*

class RecoveryActivity : AppCompatActivity(), IRecoveryView {

    private val presenter = RecoveryPresenter(this, RecoveryModel(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)


        findViewById<LinearLayout>(R.id.recovery).setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager =
                    getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
        findViewById<Button>(R.id.recoveryEnterButton).setOnClickListener { recoveryEmail() }
        findViewById<TextView>(R.id.recoveryCancel).setOnClickListener { finish() }
    }

    override fun backToMainActivity() {
        startActivity(Intent(Intent(this, AuthActivity::class.java)))
    }

    override fun recoveryEmail() {
        presenter.sendRecoveryEmail(recoveryLogin.text.toString())
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) recoveryProgressBar.visibility = View.VISIBLE
        else recoveryProgressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
