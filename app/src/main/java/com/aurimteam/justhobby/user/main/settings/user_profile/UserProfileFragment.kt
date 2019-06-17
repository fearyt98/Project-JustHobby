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
import android.support.v7.widget.SwitchCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.SuggestResponse
import com.aurimteam.justhobby.user.main.settings.settings.SettingsFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_settings_profile.*

class UserProfileFragment : Fragment(), IUserProfileView {

    private val presenter = UserProfilePresenter(this, UserProfileModel())
    private var filePath: String? = null
    private var oldPass: String = ""
    private var newPass: String = ""
    private var newRepeatPass: String = ""
    private var isTouched = false
    private var adapter: AddressArrayAdapter? = null
    private var dialog: Dialog? = null
    private var dialogDismiss = true
    private var setGps = false
    private var setImage = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings_profile, container, false)
        view.findViewById<ImageView>(R.id.userProfileBtnBack).setOnClickListener { backToSettings() }
        view.findViewById<ImageView>(R.id.userProfileBtnSend).setOnClickListener { sendChangeUserInfo() }
        view.findViewById<ImageView>(R.id.changeUserPhotoProfileBtn).setOnClickListener { pickImage() }
        view.findViewById<TextView>(R.id.changePasswordUserProfileBtn).setOnClickListener { changePasswords() }

        val gps = Settings(context!!).getPropertyBoolean("gps", false)
        view.findViewById<SwitchCompat>(R.id.locationUserProfile).isChecked = gps == true

        view.findViewById<SwitchCompat>(R.id.locationUserProfile)
            .setOnTouchListener { _, _ ->
                isTouched = true
                false
            }
        view.findViewById<SwitchCompat>(R.id.locationUserProfile)
            .setOnCheckedChangeListener { _, isChecked ->
                if (isTouched) {
                    isTouched = false
                    setGps = isChecked
                }
            }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == App.IMAGE_PICK_CODE) {
            userPhotoProfile.setImageBitmap(null)
            Glide.with(this).load(data?.data).circleCrop().into(userPhotoProfile)
            filePath = data?.data?.path
            setImage = true
        }
    }

    private fun setGps() {
        Settings(context!!).setPropertyBoolean("gps", true)
    }

    private fun unsetGps() {
        Settings(context!!).setPropertyBoolean("gps", false)
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

    override fun setSuggests(data: List<SuggestResponse>) {
        val suggestList = mutableListOf<String>()
        for (item in data)
            suggestList.add(item.address)
        adapter = AddressArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, suggestList)
        changeAddressUserProfile.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        toggleContentPB(false)
        dialog = Dialog(this.context!!)
        presenter.getUserInfo(context)
        changeAddressUserProfile.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                presenter.getAddressList(context!!, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun passwordsSuccess() {
        if (!dialogDismiss) {
            toggleContentPB(false)
            dialogDismiss = true
            dialog?.dismiss()
        }
    }

    override fun errorFirstName(message: String) {
        firstNameError.text = message
    }

    override fun errorLastName(message: String) {
        lastNameError.text = message
    }

    override fun errorPasswordNew(message: String) {
        dialog?.findViewById<TextView>(R.id.newPasswordError)?.text = message
    }

    override fun errorPasswordOld(message: String) {
        dialog?.findViewById<TextView>(R.id.oldPasswordError)?.text = message
    }

    override fun errorConfirmPass(message: String) {
        dialog?.findViewById<TextView>(R.id.confirmPasswordError)?.text = message
    }

    override fun hideOtherError() {
        lastNameError.text = ""
        firstNameError.text = ""
    }

    override fun hidePasswordError() {
        if (!dialogDismiss) {
            dialog?.findViewById<TextView>(R.id.oldPasswordError)?.text = ""
            dialog?.findViewById<TextView>(R.id.newPasswordError)?.text = ""
            dialog?.findViewById<TextView>(R.id.confirmPasswordError)?.text = ""
        }
    }

    override fun userNameClear(message: String) {
        firstNameError.text = message
    }

    override fun userLastNameClear(message: String) {
        lastNameError.text = message
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("imageSet", true)
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
        address: String?,
        image: String?
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
        if (image != null && !setImage) {
            Glide.with(this).load(image).circleCrop().into(userPhotoProfile)
        }

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
        dialogDismiss = false
        dialog?.setContentView(R.layout.activity_popup_change_passwords)
        dialog?.findViewById<TextView>(R.id.popupUserProfileOk)?.setOnClickListener {
            oldPass = dialog?.findViewById<TextInputEditText>(R.id.popupOldPass)?.text.toString()
            newPass = dialog?.findViewById<TextInputEditText>(R.id.popupNewPass)?.text.toString()
            newRepeatPass = dialog?.findViewById<TextInputEditText>(R.id.popupRepeatPass)?.text.toString()
            presenter.sendUserInfo(
                changeNameUserProfile.text.toString(),
                changeLastNameUserProfile.text.toString(),
                oldPass,
                newPass,
                newRepeatPass,
                if (locationUserProfile.isChecked) null
                else changeAddressUserProfile.text.toString(),
                context,
                dialogDismiss
            )
        }
        dialog?.findViewById<TextView>(R.id.popupUserProfileCancel)?.setOnClickListener {
            dialog?.dismiss()
            dialogDismiss = true
        }
        dialog?.window?.setBackgroundDrawableResource(R.drawable.popup_bg)
        dialog?.show()
    }

    private fun sendChangeUserInfo() {
        if (filePath != null) presenter.sendUserImage(filePath, context)
        if (dialogDismiss) {
            hideOtherError()
            if (setGps != Settings(context!!).getPropertyBoolean("gps", false) && !setGps) setGps()
            else unsetGps()
        } else hidePasswordError()
        presenter.sendUserInfo(
            changeNameUserProfile.text.toString(),
            changeLastNameUserProfile.text.toString(),
            null,
            null,
            null,
            if (locationUserProfile.isChecked) null
            else changeAddressUserProfile.text.toString(),
            context,
            dialogDismiss
        )
    }

    override fun userInfoSended() {
        if (locationUserProfile.isChecked) setGps()
        else unsetGps()
        if (dialogDismiss) {
            toggleContentPB(false)
            fragmentManager!!
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainNavContainerFragment, SettingsFragment())
                .commit()
            dialogDismiss = false
        }
        toggleContentPB(false)
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