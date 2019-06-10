package com.aurimteam.justhobby.start.recovery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aurimteam.justhobby.R
import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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

    override fun onStart() {
        super.onStart()
        if(presenter.isSetView())
            presenter.attachViewContext(this, this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun back() {
        startActivity(Intent(Intent(this, AuthActivity::class.java)))
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun recoveryEmail() {
        presenter.sendRecoveryEmail(recoveryLogin.text.toString())
    }

    override fun changeLengthEmail(message: String) {
        emailErrorRecovery.text = message
    }

    override fun clearEmailError(message: String) {
        emailErrorRecovery.text = message
    }

    override fun emailError(message: String) {
        emailErrorRecovery.text = message
    }

    override fun hideError() {
        emailErrorRecovery.text = ""
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) recoveryProgressBar.visibility = View.VISIBLE
        else recoveryProgressBar.visibility = View.GONE
    }
}
