package com.aurimteam.justhobby.MainActivity

class MainPresenter(private var view: IView?, private val model: IModel?) : MainModel.OnFinishedListener
{
    /*
    Present работает только с интерфейсом View (и методами интерфейса)
    Сами методы View не используются в Presenter
    Таким образом выполняется связь между Presenter и View */

    override fun onResultSuccess() {
        view!!.validEnter()
    }
    override fun onResultFail() {
        //ODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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