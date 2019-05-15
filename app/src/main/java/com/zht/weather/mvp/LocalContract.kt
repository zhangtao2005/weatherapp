package com.zht.weather.mvp

import com.zht.weather.WeatherData

interface LocalContract{
    interface Presenter: BasePresenter {
        fun loadWeather()
        fun addLocation()
    }

    interface View: BaseView<Presenter> {
        fun showWeatherOnLocation(weather:WeatherData)
        fun showErrorOnGetLocalWeather()
        fun onAddClicked()
    }
}