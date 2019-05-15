package com.zht.weather.model

import com.zht.weather.net.NetworkManager
import com.zht.weather.utils.ScheduleUtils
import interfaces.heweather.com.interfacesmodule.bean.weather.Weather
import io.reactivex.Observable

/**
 *   author  :zhangtao
 *   date    :2019/5/9 17:59
 *   desc    :
 */
class WeatherModel {
    fun getWeather(city:String):Observable<Weather>{
        return NetworkManager.service.loadWeather(city).compose(ScheduleUtils.ioToMain())
    }
}