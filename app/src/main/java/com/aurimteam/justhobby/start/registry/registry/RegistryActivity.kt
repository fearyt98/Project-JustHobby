package com.aurimteam.justhobby.start.registry.registry

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.start.registry.start.RegistryStartActivity
import kotlinx.android.synthetic.main.activity_registry.*

class RegistryActivity : AppCompatActivity(), IRegistryView {
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

        findViewById<TextView>(R.id.registryCancel).setOnClickListener {
            finish()
        }

        val registry = findViewById<TextView>(R.id.registryEnterButton)
        registry.setOnClickListener { regBtnClick() }
    }


    private fun changeButtonVisible(password: EditText, btn: ImageButton) {
        if (password.isFocused) btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        else btn.clearColorFilter()
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            btn.setImageResource(R.drawable.ic_visibility_off_24dp)
        } else {
            btn.setImageResource(R.drawable.ic_visibility_24dp)
        }
    }

    private fun changeVisiblePassword(password: EditText, btn: ImageButton) {
        if (password.isFocused) btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        else btn.clearColorFilter()
        val oldPosCursor = password.selectionStart
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            btn.setImageResource(R.drawable.ic_visibility_24dp)
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            btn.setImageResource(R.drawable.ic_visibility_off_24dp)
            password.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        password.setSelection(oldPosCursor)
    }

    fun regBtnClick() {
        val intent = Intent(this, RegistryStartActivity::class.java)
        intent.putExtra("email", registryLogin.text.toString())
        intent.putExtra("password", registryPassword.text.toString())
        intent.putExtra("confirm_password", registryConfirmPassword.text.toString())
        startActivity(intent)
    }
}
