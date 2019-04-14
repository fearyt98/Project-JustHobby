package com.aurimteam.justhobby.RecoveryActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aurimteam.justhobby.R
import android.widget.EditText
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


class RecoveryActivity : AppCompatActivity() {
    private fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
    private fun onFocusChange(v: View, hasFocus: Boolean) {
        if (!hasFocus) {
            hideKeyboard(v)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)
        val login = findViewById<EditText>(R.id.recoveryLogin)
        login.setOnFocusChangeListener { view, b -> onFocusChange(view, b) }
    }
}
