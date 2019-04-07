package com.aurimteam.justhobby.MainActivity

class MainPresenter(private var view: IView?, private val model: IModel?){
    /*
    Present работает только с интерфейсом View (и методами интерфейса)
    Сами методы View не используются в Presenter
    Таким образом выполняется связь между Presenter и View
     */

    fun gettingUserData(loginMain: String, password: String){

    }
    fun gettingUserDataVK(){

    }
    fun gettingUserDataFB(){

    }
    fun gettingUserDataGoogle(){

    }
}