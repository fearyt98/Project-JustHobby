package com.aurimteam.justhobby.user.main.settings.user_profile

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Space
import android.widget.TextView
import android.widget.Toast
import com.aurimteam.justhobby.App
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Settings
import com.aurimteam.justhobby.response.SuggestResponse
import com.aurimteam.justhobby.response.UserResponse
import com.aurimteam.justhobby.user.main.main_nav.MainNavActivity
import com.aurimteam.justhobby.user.main.settings.settings.SettingsFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_settings_profile.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class UserProfileFragment : Fragment(), IUserProfileView {

    private val presenter = UserProfilePresenter(this, UserProfileModel())
    private var filePath: Uri? = null
    private var oldPass: String = ""
    private var newPass: String = ""
    private var newConfirmPass: String = ""
    private var isTouched = false
    private var adapter: AddressArrayAdapter? = null
    private var dialog: Dialog? = null
    private var setImage = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings_profile, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == App.IMAGE_PICK_CODE) {
            userPhotoProfile.setImageBitmap(null)
            Glide.with(this).load(data?.data).circleCrop().into(userPhotoProfile)
            filePath = data?.data
            setImage = true
        }
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
        userProfileBtnBack.setOnClickListener { backToSettings() }
        userProfileBtnSend.setOnClickListener { sendChangeUserInfo() }
        changeUserPhotoProfileBtn.setOnClickListener { pickImage() }
        changePasswordUserProfileBtn.setOnClickListener { changePasswords() }

        val gps = Settings(context!!).getPropertyBoolean("gps", false)
        locationUserProfile.isChecked = gps == true

        locationUserProfile.setOnTouchListener { _, _ ->
            isTouched = true
            false
        }

        KeyboardVisibilityEvent.setEventListener(activity) { isOpen ->
            if (isOpen) {
                activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.GONE
                activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.GONE
            } else {
                activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.VISIBLE
                activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.VISIBLE
            }
        }

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
        toggleContentPB(false)
        dialog?.dismiss()
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
        dialog?.findViewById<TextView>(R.id.oldPasswordError)?.text = ""
        dialog?.findViewById<TextView>(R.id.newPasswordError)?.text = ""
        dialog?.findViewById<TextView>(R.id.confirmPasswordError)?.text = ""
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
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<BottomNavigationView>(R.id.mainNavNavigation)?.visibility = View.VISIBLE
        activity?.findViewById<Space>(R.id.mainNavMarginSpacer)?.visibility = View.VISIBLE
        presenter.detachView()
    }

    override fun setUserDefaultInfo(user: UserResponse) {
        toggleContentPB(false)
        changeEmailUserProfile.setText(
            user.attributes.email,
            TextView.BufferType.EDITABLE
        )
        changeNameUserProfile.setText(
            user.attributes.first_name,
            TextView.BufferType.EDITABLE
        )
        changeLastNameUserProfile.setText(
            user.attributes.last_name,
            TextView.BufferType.EDITABLE
        )
        if (user.attributes.address != null)
            changeAddressUserProfile.setText(
                user.attributes.address,
                TextView.BufferType.EDITABLE
            )
        if (user.attributes.avatar != null && !setImage)
            Glide.with(this).load(user.attributes.avatar).circleCrop().into(userPhotoProfile)

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

    override fun userInfoSended() {
        toggleContentPB(false)
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, SettingsFragment())
            .commit()
    }

    override fun showMessage(message: String?) {
        val devMode = Settings(context!!).getPropertyBoolean("dev_mode", false)
        if (devMode != null && devMode) {
            val toast = Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.BOTTOM, 0, 30)
            toast.show()
        }
        toggleContentPB(false)
    }

    private fun setGps() {
        Settings(context!!).setPropertyBoolean("gps", true)
        val gpsData = (activity as MainNavActivity).gpsData
        if (!gpsData.isCreated) {
            gpsData.create(activity!!, context!!)
        } else
            gpsData.activate()
    }

    private fun unsetGps() {
        Settings(context!!).setPropertyBoolean("gps", false)
        val gpsData = (activity as MainNavActivity).gpsData
        if (gpsData.isCreated)
            gpsData.deactivate()
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
        val mimeTypes = arrayListOf("image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(intent, App.IMAGE_PICK_CODE)
    }

    private fun changePasswords() {
        dialog?.setContentView(R.layout.dialog_change_passwords)
        dialog?.findViewById<TextView>(R.id.popupUserProfileOk)?.setOnClickListener {
            oldPass = dialog?.findViewById<TextInputEditText>(R.id.popupOldPass)?.text.toString()
            newPass = dialog?.findViewById<TextInputEditText>(R.id.popupNewPass)?.text.toString()
            newConfirmPass = dialog?.findViewById<TextInputEditText>(R.id.popupRepeatPass)?.text.toString()
            presenter.sendUserPassword(
                oldPass,
                newPass,
                newConfirmPass,
                context
            )
        }
        dialog?.findViewById<TextView>(R.id.popupUserProfileCancel)?.setOnClickListener {
            dialog?.dismiss()
        }
        dialog?.window?.setBackgroundDrawableResource(R.drawable.popup_bg)
        dialog?.show()
    }

    private fun sendChangeUserInfo() {
        if (filePath != null) presenter.sendUserImage(filePath!!, context)

        hideOtherError()

        if (locationUserProfile.isChecked)
            setGps()
        else
            unsetGps()

        presenter.sendUserInfo(
            changeNameUserProfile.text.toString(),
            changeLastNameUserProfile.text.toString(),
            changeAddressUserProfile.text.toString(),
            context
        )
    }

    private fun backToSettings() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, SettingsFragment())
            .commit()
    }
}