package com.aurimteam.justhobby.AuthActivity

class AuthPresenter(private var view: IAuthView?, private val model: IAuthModel?) : AuthModel.OnFinishedListener
{
    /*
    Present работает только с интерфейсом View (и методами интерфейса)
    Сами методы View не используются в Presenter
    Таким образом выполняется связь между Presenter и View */

    override fun onResultSuccess() {
        view!!.validEnter()
    }
    override fun onResultFail() {

    }
    fun gettingUserData(loginMain: String, password: String){
        model?.checkUserData(this)

    }
    fun gettingUserDataVK(){

    }
    fun gettingUserDataFB(){

    }
    fun gettingUserDataGoogle(){

    }
    fun onDestroy() {
        view = null
    }
}