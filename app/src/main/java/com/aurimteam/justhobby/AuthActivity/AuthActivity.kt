package com.aurimteam.justhobby.AuthActivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RegistryActivity.RegistryActivity
import kotlinx.android.synthetic.main.activity_auth_main.*
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import com.aurimteam.justhobby.Main.MainNav
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.MainRecommendationFragment
import com.aurimteam.justhobby.RecoveryActivity.RecoveryActivity

class AuthActivity : AppCompatActivity(), IAuthView {
    /*
    Активити обращается только к методам презентера, передаем ему введенную информацию
    или выводит полученную от презентера
     */
    private lateinit var authPresenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_main)

        authPresenter = AuthPresenter(this, AuthModel())

        val buttonEnter = findViewById<Button>(R.id.authEnterButton)
        val buttonVK = findViewById<Button>(R.id.authVkEnter)
        /*val buttonGoogle = findViewById<Button>(R.id.authGoogleEnter)
        val buttonFB = findViewById<Button>(R.id.authFacebookEnter)*/
        val forget = findViewById<TextView>(R.id.authForget)
        val registry = findViewById<TextView>(R.id.authRegistry)

        buttonEnter.setOnClickListener { getUserData() }
        buttonVK.setOnClickListener { getUserDataVK() }
        /*buttonGoogle.setOnClickListener { getUserDataGoogle() }
        buttonFB.setOnClickListener { getUserDataFB() }*/
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
        if (password.isFocused) {
            btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        } else {
            btn.clearColorFilter()
        }
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            btn.setImageResource(R.drawable.ic_visibility_off_24dp)
        } else {
            btn.setImageResource(R.drawable.ic_visibility_24dp)
        }
    }

    private fun changeVisiblePassword(password: EditText, btn: ImageButton) {
        if (password.isFocused) {
            btn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        } else {
            btn.clearColorFilter()
        }
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

    override fun getUserData() {
        authPresenter.gettingUserData(authLogin.text.toString(), authPassword.text.toString())
    }

    override fun getUserDataFB() {

    }

    override fun getUserDataVK() {
        val intent = Intent(this, MainNav::class.java)
        startActivity(intent)
    }

    override fun getUserDataGoogle() {

    }

    override fun forgetChangeActivity() {
        val intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }

    override fun registryChangeActivity() {
        val intent = Intent(this, RegistryActivity::class.java)
        startActivity(intent)
    }

    override fun validEnter() {
        val intent = Intent(this, RegistryActivity::class.java)
        startActivity(intent)
    }

    override fun setDataError() {
        // Show error on UI strError: String
    }

    override fun onDestroy() {
        authPresenter.onDestroy()
        super.onDestroy()
    }
}
