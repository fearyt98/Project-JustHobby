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

    private val presenter = RegistryPresenter(this, RegistryModel(), this)

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
        val btnChangeVisible = findViewById<ImageButton>(R.id.registryPasswordVisible)
        password.setOnFocusChangeListener { _: View, _: Boolean ->
            changeButtonVisible(password, btnChangeVisible)
        }
        btnChangeVisible.setOnClickListener {
            changeVisiblePassword(password, btnChangeVisible)
        }

        val confirmPassword = findViewById<EditText>(R.id.registryConfirmPassword)
        val btnChangeVisibleConfirm = findViewById<ImageButton>(R.id.registryConfirmPasswordVisible)
        confirmPassword.setOnFocusChangeListener { _: View, _: Boolean ->
            changeButtonVisible(confirmPassword, btnChangeVisibleConfirm)
        }
        btnChangeVisibleConfirm.setOnClickListener {
            changeVisiblePassword(confirmPassword, btnChangeVisibleConfirm)
        }

        findViewById<TextView>(R.id.registryCancel).setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.registryEnterButton).setOnClickListener { regBtnClick() }
    }

    override fun openRegistryStart() {
        startActivity(Intent(Intent(this, RegistryStartActivity::class.java)))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            registryProgressBar.visibility = View.VISIBLE
        } else {
            registryProgressBar.visibility = View.GONE
        }
    }

    private fun changeButtonVisible(password: EditText, btn: ImageButton) {
        if (password.isFocused) {
            btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        } else {
            btn.clearColorFilter()
        }

        btn.setImageResource(
            if (isVisiblePassword(password)) {
                R.drawable.ic_visibility_off_24dp
            } else {
                R.drawable.ic_visibility_24dp
            }
        )
    }

    private fun changeVisiblePassword(password: EditText, btn: ImageButton) {
        if (password.isFocused) btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        else btn.clearColorFilter()
        val oldPosCursor = password.selectionStart

        btn.setImageResource(
            if (isVisiblePassword(password)) {
                R.drawable.ic_visibility_24dp
            } else {
                R.drawable.ic_visibility_off_24dp
            }
        )
        setVisiblePassword(password, isVisiblePassword(password))

        password.setSelection(oldPosCursor)
    }

    private fun isVisiblePassword(password: EditText): Boolean {
        return password.transformationMethod == PasswordTransformationMethod.getInstance()
    }

    private fun setVisiblePassword(password: EditText, isVisible: Boolean) {
        password.transformationMethod = if (isVisible) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    private fun regBtnClick() {
        presenter.sendUserInfo(
            registryLogin.text.toString(),
            registryPassword.text.toString(),
            registryConfirmPassword.text.toString()
        )
    }
}
