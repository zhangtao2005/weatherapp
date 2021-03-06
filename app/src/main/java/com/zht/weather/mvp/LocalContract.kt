package com.zht.weather.mvp

import com.zht.weather.WeatherData

interface LocalContract{
    interface Presenter: BasePresenter {
        fun loadWeather(city:String?)
    }

    interface View: BaseView {
        fun showWeatherOnLocation(weather:WeatherData)
        fun showErrorOnGetLocalWeather(city:String)
    }
}