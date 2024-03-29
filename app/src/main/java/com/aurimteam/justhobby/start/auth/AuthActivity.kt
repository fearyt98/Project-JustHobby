package com.aurimteam.justhobby.start.auth

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.start.registry.registry.RegistryActivity
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.widget.*
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import com.aurimteam.justhobby.start.recovery.RecoveryActivity
import com.aurimteam.justhobby.start.registry.start.RegistryStartActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), IAuthView {
    /* Активити обращается только к методам презентера, передаем ему введенную информацию
    или выводит полученную от презентера */
    private val presenter = AuthPresenter(this, AuthModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        presenter.isSetToken()

        findViewById<Button>(R.id.authEnterButton).setOnClickListener { loginUser() }
        findViewById<TextView>(R.id.authForget).setOnClickListener { forgetChangeActivity() }
        findViewById<TextView>(R.id.authRegistry).setOnClickListener { registryChangeActivity() }
    }

    override fun onResume() {
        super.onResume()
        findViewById<LinearLayout>(R.id.auth).setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
        val password = findViewById<EditText>(R.id.authPassword)
        val btnChangeVisible = findViewById<ImageButton>(R.id.authPasswordVisible)
        password.setOnFocusChangeListener { _: View, _: Boolean ->
            changeButtonVisible(password, btnChangeVisible)
        }
        btnChangeVisible.setOnClickListener {
            changeVisiblePassword(password, btnChangeVisible)
        }
    }

    override fun onStart() {
        super.onStart()
        if(!presenter.isSetView())
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

    override fun loginUser() {
        presenter.loginUser(authLogin.text.toString(), authPassword.text.toString())
    }

    override fun openMain() {
        startActivity(Intent(this, MainNavActivity::class.java))
        finish()
    }

    override fun openStartRegistry() {
        startActivity(Intent(this, RegistryStartActivity::class.java))
        finish()
    }

    override fun hideErrors() {
        emailErrorAuth.text = ""
        passwordErrorAuth.text = ""
    }

    override fun showMessage(message: String?) {
        val devMode = Settings(this).getPropertyBoolean("dev_mode", false)
        if(devMode != null && devMode) {
            val toast = Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
        togglePB(false)
    }

    override fun changeLengthPassword(message: String) {
        passwordErrorAuth.text = message
    }

    override fun changeLengthEmail(message: String) {
        emailErrorAuth.text = message
    }

    override fun clearEmailError(message: String) {
        emailErrorAuth.text = message
    }

    override fun clearPasswordError(message: String) {
        passwordErrorAuth.text = message
    }

    override fun emailOrPasswordError(message: String) {
        emailErrorAuth.text = message
        passwordErrorAuth.text = message
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) authProgressBar.visibility = View.VISIBLE
        else authProgressBar.visibility = View.GONE

    }

    private fun changeButtonVisible(password: EditText, btn: ImageButton) {
        if (password.isFocused) btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        else btn.clearColorFilter()
        btn.setImageResource(
            if (isVisiblePassword(password)) R.drawable.ic_visibility_off_24dp
            else R.drawable.ic_visibility_24dp
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

    private fun forgetChangeActivity() {
        startActivity(Intent(this, RecoveryActivity::class.java))
    }

    private fun registryChangeActivity() {
        startActivity(Intent(this, RegistryActivity::class.java))
    }

}
