package com.zht.weather.mvp

interface MainContract{
    interface Presenter: BasePresenter {
        fun loadWeathers()
    }

    interface View: BaseView<Presenter> {
        fun onGetAllCityNames(set:MutableSet<String>)
        fun onTabSelected(index:Int)
        fun onAddClicked()
    }
}