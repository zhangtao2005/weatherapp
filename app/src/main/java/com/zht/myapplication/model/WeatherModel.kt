package com.zht.myapplication.model

import com.zht.myapplication.net.NetworkManager
import com.zht.myapplication.Weather
import com.zht.myapplication.utils.ScheduleUtils
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