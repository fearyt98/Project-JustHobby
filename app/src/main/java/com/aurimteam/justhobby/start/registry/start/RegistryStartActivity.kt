package com.aurimteam.justhobby.start.registry.start

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.aurimteam.justhobby.App.Companion.IMAGE_PICK_CODE
import com.aurimteam.justhobby.App.Companion.PERMISSION_CODE
import com.aurimteam.justhobby.start.features.FeaturesActivity
import com.aurimteam.justhobby.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_registry_start.*

class RegistryStartActivity : AppCompatActivity(), IRegistryStartView {

    private val presenter = RegistryStartPresenter(this, RegistryStartModel(), this)
    private var filePath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_start)
        findViewById<Button>(R.id.registryStartReadyBtn).setOnClickListener { readyBtnClick() }
        findViewById<ImageButton>(R.id.userPhotoBtn).setOnClickListener { pickImage() }
        findViewById<TextView>(R.id.registryStartUserAgreement).setOnClickListener { openUserAgreement() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Glide.with(this).load(data?.data).circleCrop().into(userPhoto)
            filePath = data?.data?.path
        }
    }

    private fun pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            } else pickImageFromGallery()
        else pickImageFromGallery()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    pickImageFromGallery()
                else Toast.makeText(this, "Загрузка изображения запрещена пользователем", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(presenter.isSetView())
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

    override fun clearFirstName(message: String) {
        firstNameErrorRegistryStart.text = message
    }

    override fun clearLastName(message: String) {
        lastNameErrorRegistryStart.text = message
    }

    override fun hideErrors() {
        firstNameErrorRegistryStart.text = ""
        lastNameErrorRegistryStart.text = ""
    }

    override fun changeLengthFirstName(message: String) {
        firstNameErrorRegistryStart.text = message
    }

    override fun changeLengthLastName(message: String) {
        lastNameErrorRegistryStart.text = message
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    override fun userRegistered() {
        startActivity(Intent(this, FeaturesActivity::class.java))
    }

    override fun togglePB(isVisiblePB: Boolean) {
        if (isVisiblePB) registryStartProgressBar.visibility = View.VISIBLE
        else registryStartProgressBar.visibility = View.GONE
    }

    private fun readyBtnClick() {
        presenter.sendUserImage(filePath)
        presenter.sendUserInfo(registryStartFirstName.text.toString(), registryStartLastName.text.toString())
    }

    private fun openUserAgreement() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
    }
}