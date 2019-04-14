package com.aurimteam.justhobby.RegistryActivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RecoveryActivity.RecoveryActivity

class RegistryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        val registryView = findViewById<RelativeLayout>(R.id.registry)
        registryView.setOnFocusChangeListener { view: View, b: Boolean ->
            if (b) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val password = findViewById<EditText>(R.id.registryPassword)
        password.setOnFocusChangeListener { _: View, _: Boolean ->
            val password = findViewById<EditText>(R.id.registryPassword)
            val btn = findViewById<ImageButton>(R.id.registryPasswordVisible)
            changeButtonVisible(password, btn)
        }
        val buttonChangeVisiblePassword = findViewById<ImageButton>(R.id.registryPasswordVisible)
        buttonChangeVisiblePassword.setOnClickListener {
            val password = findViewById<EditText>(R.id.registryPassword)
            val btn = findViewById<ImageButton>(R.id.registryPasswordVisible)
            changeVisiblePassword(password, btn)
        }

        val confirmPassword = findViewById<EditText>(R.id.registryConfirmPassword)
        confirmPassword.setOnFocusChangeListener { _: View, _: Boolean ->
            val password = findViewById<EditText>(R.id.registryConfirmPassword)
            val btn = findViewById<ImageButton>(R.id.registryConfirmPasswordVisible)
            changeButtonVisible(password, btn)
        }
        val buttonChangeVisibleConfirmPassword = findViewById<ImageButton>(R.id.registryConfirmPasswordVisible)
        buttonChangeVisibleConfirmPassword.setOnClickListener {
            val password = findViewById<EditText>(R.id.registryConfirmPassword)
            val btn = findViewById<ImageButton>(R.id.registryConfirmPasswordVisible)
            changeVisiblePassword(password, btn)
        }

        val cancel = findViewById<TextView>(R.id.registryCancel)
        cancel.setOnClickListener {
            finish()
        }
    }

    private fun changeButtonVisible(password: EditText, btn: ImageButton) {
        val color = if (password.isFocused) "green" else "white"
        if(password.transformationMethod != PasswordTransformationMethod.getInstance()) {
            val resId = resources.getIdentifier("ic_visibility_off_" + color + "_24dp", "drawable", applicationContext.packageName)
            btn.setImageResource(resId)
        } else {
            val resId = resources.getIdentifier("ic_visibility_" + color + "_24dp", "drawable", applicationContext.packageName)
            btn.setImageResource(resId)
        }
    }

    private fun changeVisiblePassword(password: EditText, btn: ImageButton) {
        val color = if (password.isFocused) "green" else "white"
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            val resId = resources.getIdentifier("ic_visibility_off_" + color + "_24dp", "drawable", applicationContext.packageName)
            btn.setImageResource(resId)
            val oldPosCursor = password.selectionStart
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            password.setSelection(oldPosCursor)
        } else {
            val resId = resources.getIdentifier("ic_visibility_" + color + "_24dp", "drawable", applicationContext.packageName)
            btn.setImageResource(resId)
            val oldPosCursor = password.selectionStart
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            password.setSelection(oldPosCursor)
        }
    }

    fun regBtnClick(view: View){
        var intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }
}
