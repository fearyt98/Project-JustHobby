package com.aurimteam.justhobby.RegistryActivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.aurimteam.justhobby.FeaturesActivities.FeaturesActivity
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RecoveryActivity.RecoveryActivity

class RegistryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        val registryView = findViewById<LinearLayout>(R.id.registry)
        registryView.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val password = findViewById<EditText>(R.id.registryPassword)
        val buttonChangeVisiblePassword = findViewById<ImageButton>(R.id.registryPasswordVisible)
        password.setOnFocusChangeListener { _: View, _: Boolean ->
            changeButtonVisible(password, buttonChangeVisiblePassword)
        }
        buttonChangeVisiblePassword.setOnClickListener {
            changeVisiblePassword(password, buttonChangeVisiblePassword)
        }

        val confirmPassword = findViewById<EditText>(R.id.registryConfirmPassword)
        val buttonChangeVisibleConfirmPassword = findViewById<ImageButton>(R.id.registryConfirmPasswordVisible)
        confirmPassword.setOnFocusChangeListener { _: View, _: Boolean ->
            changeButtonVisible(confirmPassword, buttonChangeVisibleConfirmPassword)
        }
        buttonChangeVisibleConfirmPassword.setOnClickListener {
            changeVisiblePassword(confirmPassword, buttonChangeVisibleConfirmPassword)
        }

        val cancel = findViewById<TextView>(R.id.registryCancel)
        cancel.setOnClickListener {
            finish()
        }

        val registry = findViewById<TextView>(R.id.registryEnterButton)
        registry.setOnClickListener {
            val intent = Intent(this, FeaturesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun changeButtonVisible(password: EditText, btn: ImageButton) {
        val color = if (password.isFocused) "green" else "white"
        if (password.transformationMethod != PasswordTransformationMethod.getInstance()) {
            val resId = resources.getIdentifier(
                "ic_visibility_off_" + color + "_24dp",
                "drawable",
                applicationContext.packageName
            )
            btn.setImageResource(resId)
        } else {
            val resId =
                resources.getIdentifier("ic_visibility_" + color + "_24dp", "drawable", applicationContext.packageName)
            btn.setImageResource(resId)
        }
    }

    private fun changeVisiblePassword(password: EditText, btn: ImageButton) {
        val color = if (password.isFocused) "green" else "white"
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            val resId = resources.getIdentifier(
                "ic_visibility_off_" + color + "_24dp",
                "drawable",
                applicationContext.packageName
            )
            btn.setImageResource(resId)
            val oldPosCursor = password.selectionStart
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            password.setSelection(oldPosCursor)
        } else {
            val resId =
                resources.getIdentifier("ic_visibility_" + color + "_24dp", "drawable", applicationContext.packageName)
            btn.setImageResource(resId)
            val oldPosCursor = password.selectionStart
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            password.setSelection(oldPosCursor)
        }
    }

    fun regBtnClick(view: View) {
        var intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }
}
