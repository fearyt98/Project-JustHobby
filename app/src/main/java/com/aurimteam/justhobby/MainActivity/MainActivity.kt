package com.aurimteam.justhobby.MainActivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RecoveryActivity.RecoveryActivity
import com.aurimteam.justhobby.RegistryActivity.RegistryActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*

class MainActivity : AppCompatActivity(), IView {
    /*
    Активити обращается только к методам презентера, передаем ему введенную информацию
    или выводит полученную от презентера
     */
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this, MainModel())

        val mainView = findViewById<RelativeLayout>(R.id.main)
        mainView.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (hasFocus) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val password = findViewById<EditText>(R.id.mainPassword)
        password.setOnFocusChangeListener { _: View, _: Boolean ->
            val password = findViewById<EditText>(R.id.mainPassword)
            val btn = findViewById<ImageButton>(R.id.mainPasswordVisible)
            changeButtonVisible(password, btn)
        }
        val buttonChangeVisiblePassword = findViewById<ImageButton>(R.id.mainPasswordVisible)
        buttonChangeVisiblePassword.setOnClickListener {
            val password = findViewById<EditText>(R.id.mainPassword)
            val btn = findViewById<ImageButton>(R.id.mainPasswordVisible)
            changeVisiblePassword(password, btn)
        }

        val buttonEnter = findViewById<Button>(R.id.mainEnterButton)
        val buttonVK = findViewById<Button>(R.id.mainVkEnter)
        val buttonGoogle = findViewById<Button>(R.id.mainGoogleEnter)
        val buttonFB = findViewById<Button>(R.id.mainFacebookEnter)
        val forget = findViewById<TextView>(R.id.mainForget)
        val registry = findViewById<TextView>(R.id.mainRegistry)

        buttonEnter.setOnClickListener { getUserData() }
        buttonVK.setOnClickListener { getUserDataVK() }
        buttonGoogle.setOnClickListener { getUserDataGoogle() }
        buttonFB.setOnClickListener { getUserDataFB() }
        forget.setOnClickListener { forgetChangeActivity() }
        registry.setOnClickListener { registryChangeActivity() }
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

    override fun getUserData() {
        mainPresenter.gettingUserData(mainLogin.text.toString(), mainPassword.text.toString())
        val intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }

    override fun getUserDataFB() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserDataVK() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserDataGoogle() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun forgetChangeActivity() {
        val intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }

    override fun registryChangeActivity() {
        val intent = Intent(this, RegistryActivity::class.java)
        startActivity(intent)
    }
}
