package com.zht.myapplication.mvp

import com.zht.myapplication.Weather

interface LocalContract{
    interface Presenter: BasePresenter {
        fun loadWeather()
        fun addLocation()
    }

    interface View: BaseView<Presenter> {
        fun showWeatherOnLocation(weather:Weather)
        fun showErrorOnGetLocalWeather()
        fun onAddClicked()
    }
}