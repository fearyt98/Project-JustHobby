package com.aurimteam.justhobby.AuthActivity

class AuthModel: IAuthModel{

    interface OnFinishedListener {
        fun onResultSuccess()  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun checkUserData(onFinishedListener: OnFinishedListener) {
    }
}