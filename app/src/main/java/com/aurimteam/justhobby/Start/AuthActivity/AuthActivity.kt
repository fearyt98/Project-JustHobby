package com.aurimteam.justhobby.Start.AuthActivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Start.RegistryActivity.RegistryActivity
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import com.aurimteam.justhobby.Main.MainNav
import com.aurimteam.justhobby.Start.RecoveryActivity.RecoveryActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), IAuthView {
    /*
    Активити обращается только к методам презентера, передаем ему введенную информацию
    или выводит полученную от презентера
     */
    private val authPresenter = AuthPresenter(this, AuthModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        authPresenter.isSetToken()

        val buttonEnter = findViewById<Button>(R.id.authEnterButton)
        val forget = findViewById<TextView>(R.id.authForget)
        val registry = findViewById<TextView>(R.id.authRegistry)

        buttonEnter.setOnClickListener { loginUser() }
        forget.setOnClickListener { forgetChangeActivity() }
        registry.setOnClickListener { registryChangeActivity() }
    }

    override fun onResume() {
        super.onResume()

        val authView = findViewById<LinearLayout>(R.id.auth)
        authView.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val password = findViewById<EditText>(R.id.authPassword)
        val buttonChangeVisiblePassword = findViewById<ImageButton>(R.id.authPasswordVisible)
        password.setOnFocusChangeListener { _: View, _: Boolean ->
            changeButtonVisible(password, buttonChangeVisiblePassword)
        }
        buttonChangeVisiblePassword.setOnClickListener {
            changeVisiblePassword(password, buttonChangeVisiblePassword)
        }
    }

    private fun changeButtonVisible(password: EditText, btn: ImageButton) {
        if (password.isFocused) btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        else btn.clearColorFilter()
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            btn.setImageResource(R.drawable.ic_visibility_24dp)
        } else {
            btn.setImageResource(R.drawable.ic_visibility_off_24dp)
        }
    }

    private fun changeVisiblePassword(password: EditText, btn: ImageButton) {
        if (password.isFocused) btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        else btn.clearColorFilter()
        val oldPosCursor = password.selectionStart
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            btn.setImageResource(R.drawable.ic_visibility_off_24dp)
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            btn.setImageResource(R.drawable.ic_visibility_24dp)
            password.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        password.setSelection(oldPosCursor)
    }

    override fun loginUser() {
        authPresenter.loginUser(authLogin.text.toString(), authPassword.text.toString())
    }
    override fun validEnter() {
        startActivity(Intent(this, RegistryActivity::class.java))
    }

    override fun openMain() {
        startActivity(Intent(this, MainNav::class.java))
    }

    override fun setDataError() {
        // Show error on UI strError: String
    }

    override fun onDestroy() {
        super.onDestroy()
        authPresenter.onDestroy()
    }

    private fun forgetChangeActivity() {
        startActivity(Intent(this, RecoveryActivity::class.java))
    }

    private fun registryChangeActivity() {
        startActivity(Intent(this, RegistryActivity::class.java))
    }

}
