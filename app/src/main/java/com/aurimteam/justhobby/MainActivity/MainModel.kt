package com.aurimteam.justhobby.MainActivity

class MainModel: IModel{

    interface OnFinishedListener {
        fun onResultSuccess()  //arrUpdates: List<DataItem>
        fun onResultFail() //strError: String
    }

    override fun checkUserData(onFinishedListener: OnFinishedListener) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}