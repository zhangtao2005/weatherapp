package com.zht.myapplication.api

import com.zht.myapplication.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *   author  :zhangtao
 *   date    :2019/5/9 15:02
 *   desc    :
 */
interface ApiService{
    @GET("weatherApi?")
    fun loadWeather(@Query("city") city:String): Observable<Weather>
}