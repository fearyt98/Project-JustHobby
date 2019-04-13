package com.aurimteam.justhobby.MainActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.RecoverActivity.RecoverActivity
import com.aurimteam.justhobby.RegistryActivity.Registry
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

        val buttonEnter = findViewById<Button>(R.id.EnterButtonMain)
        val buttonVK = findViewById<Button>(R.id.vkEnterMain)
        val buttonGoogle = findViewById<Button>(R.id.googleEnterMain)
        val buttonFB = findViewById<Button>(R.id.facebookEnterMain)
        val forget = findViewById<TextView>(R.id.forgetMain)
        val registry = findViewById<TextView>(R.id.registry)

        buttonEnter.setOnClickListener { getUserData() }
        buttonVK.setOnClickListener { getUserDataVK() }
        buttonGoogle.setOnClickListener { getUserDataGoogle() }
        buttonFB.setOnClickListener { getUserDataFB() }
        forget.setOnClickListener { forgetChangeActivity() }
        registry.setOnClickListener { registryChangeActivity() }
    }
    override fun getUserData(){
        mainPresenter.gettingUserData(loginMain.text.toString(), passwordMain.text.toString())
        val intent = Intent(this,Registry::class.java)
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
        val intent = Intent(this, RecoverActivity::class.java)
        startActivity(intent)
    }
    override fun registryChangeActivity(){
        val intent = Intent(this, Registry::class.java)
        startActivity(intent)
    }
}
