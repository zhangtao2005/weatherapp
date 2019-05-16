package com.zht.weather.mvp

import android.util.Log
import com.google.gson.Gson
import com.zht.weather.MainActivity
import com.zht.weather.MyApplication
import com.zht.weather.WeatherData
import interfaces.heweather.com.interfacesmodule.bean.Code
import interfaces.heweather.com.interfacesmodule.bean.Lang
import interfaces.heweather.com.interfacesmodule.bean.Unit
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.reactivex.disposables.CompositeDisposable

class LocalWeatherPresenter: LocalContract.Presenter {
    private var mLocalView:LocalContract.View

    private val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    constructor(view:LocalContract.View){
        mLocalView = view
    }

    override fun loadWeather(city:String?) {
        mCompositeDisposable.clear()
        city?.let { getOneCityWeather(it) }
    }

    private fun getOneCityWeather(it: String) {
        HeWeather.getWeatherNow(
            MyApplication.context,
            it,
            Lang.CHINESE_SIMPLIFIED,
            Unit.METRIC,
            object : HeWeather.OnResultWeatherNowBeanListener {
                override fun onError(e: Throwable) {
                    Log.i(MainActivity.TAG, "Weather Now onError: ", e)
                }

                override fun onSuccess(dataObject: Now) {
                    Log.i(MainActivity.TAG, " Weather Now onSuccess: " + Gson().toJson(dataObject))
                    //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                    if (Code.OK.code.equals(dataObject.status, true)) {
                        //此时返回数据
                        val now = dataObject.now
                        val basic = dataObject.basic
                        val wdata = WeatherData(
                            basic.location,
                            now.tmp,
                            now.cond_txt,
                            now.cond_code,
                            now.wind_deg,
                            now.wind_dir
                        )
                        mLocalView.showWeatherOnLocation(wdata)
                    } else {
                        //在此查看返回数据失败的原因
                        val status = dataObject.status
                        val code = Code.toEnum(status)
                        Log.i(MainActivity.TAG, "failed code: $code")
                    }
                }
        })
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }
}