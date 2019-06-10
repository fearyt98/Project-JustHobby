package com.aurimteam.justhobby.user.main.settings.user_profile

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.user.main.settings.settings.SettingsFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_settings_profile.*

class UserProfileFragment : Fragment(), IUserProfileView {

    private val presenter = UserProfilePresenter(this, UserProfileModel())
    private var filePath: String? = null
    private var oldPass: String = ""
    private var newPass: String = ""
    private var newRepeatPass: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings_profile, container, false)
        view.findViewById<ImageView>(R.id.userProfileBtnBack).setOnClickListener { backToSettings() }
        view.findViewById<ImageView>(R.id.userProfileBtnSend).setOnClickListener { sendChangeUserInfo() }
        view.findViewById<ImageView>(R.id.changeUserPhotoProfileBtn).setOnClickListener { pickImage() }
        view.findViewById<TextView>(R.id.changePasswordUserProfileBtn).setOnClickListener { changePasswords() }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == App.IMAGE_PICK_CODE) {
            Glide.with(this).load(data?.data).circleCrop().into(userPhotoProfile)
            filePath = data?.data?.path
        }
    }

    private fun pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (checkSelfPermission(
                    context!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, App.PERMISSION_STORAGE_CODE)
            } else pickImageFromGallery()
        else pickImageFromGallery()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, App.IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            App.PERMISSION_STORAGE_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    pickImageFromGallery()
                else Toast.makeText(context, "Загрузка изображения запрещена пользователем", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        toggleContentPB(false)
        presenter.getUserInfo(context)
    }

    override fun onStop() {
        super.onStop()
        presenter.dettachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
    }

    override fun setUserDefaultInfo(
        email: String,
        name: String,
        lastName: String,
        address: String?
    ) {
        if (email != "") changeEmailUserProfile.setText(
            email,
            TextView.BufferType.EDITABLE
        )
        if (name != "") changeNameUserProfile.setText(
            name,
            TextView.BufferType.EDITABLE
        )
        if (lastName != "") changeLastNameUserProfile.setText(
            lastName,
            TextView.BufferType.EDITABLE
        )
        if (address != "") changeAddressUserProfile.setText(
            address,
            TextView.BufferType.EDITABLE
        )
    }

    override fun toggleContentPB(isVisiblePB: Boolean) {
        if (isVisiblePB) {
            userProfileProgressBar.visibility = View.VISIBLE
            userProfileContent.visibility = View.GONE
        } else {
            userProfileProgressBar.visibility = View.GONE
            userProfileContent.visibility = View.VISIBLE
        }
    }

    private fun changePasswords() {
        val dialog = Dialog(this.context!!)
        dialog.setContentView(R.layout.activity_popup_change_passwords)
        dialog.findViewById<TextView>(R.id.popupUserProfileOk).setOnClickListener {
            oldPass = dialog.findViewById<TextInputEditText>(R.id.popupOldPass).text.toString()
            newPass = dialog.findViewById<TextInputEditText>(R.id.popupNewPass).text.toString()
            newRepeatPass = dialog.findViewById<TextInputEditText>(R.id.popupRepeatPass).text.toString()
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.popupUserProfileCancel).setOnClickListener { dialog.dismiss() }
        dialog.window?.setBackgroundDrawableResource(R.drawable.popup_bg)
        dialog.show()
    }

    private fun sendChangeUserInfo() {
        //presenter.sendUserImage(filePath, context)
        presenter.sendUserInfo(
            changeNameUserProfile.text.toString(),
            changeLastNameUserProfile.text.toString(),
            oldPass,
            newPass,
            newRepeatPass,
            changeAddressUserProfile.text.toString(),
            context
        )
    }

    override fun showMessage(message: String?) {
        val toast = Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM, 0, 30)
        toast.show()
    }

    private fun backToSettings() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, SettingsFragment())
            .commit()
    }
}