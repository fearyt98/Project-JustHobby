package com.aurimteam.justhobby.MainActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RecoveryActivity.RecoveryActivity
import kotlinx.android.synthetic.main.activity_main.*

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
    override fun getUserData(){
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
    override fun forgetChangeActivity(){
        val intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }
    override fun registryChangeActivity(){
        val intent = Intent(this, RecoveryActivity::class.java)
        startActivity(intent)
    }
}
