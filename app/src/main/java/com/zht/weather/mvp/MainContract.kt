package com.zht.weather.mvp

interface MainContract{
    interface Presenter: BasePresenter {
        fun loadWeathers()
    }

    interface View: BaseView {
        fun onGetAllCityNames(set:MutableSet<String>)
        fun onAddClicked()
    }
}