package com.zht.weather.ui

import android.util.Log
import android.view.View
import com.zht.weather.R
import com.zht.weather.WeatherData
import com.zht.weather.base.BaseActivity
import com.zht.weather.base.BaseFragment
import com.zht.weather.mvp.LocalContract
import com.zht.weather.mvp.LocalWeatherPresenter
import com.zht.weather.showToast
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.IconUtils
import kotlinx.android.synthetic.main.fragment_main_item.*
import org.joda.time.DateTime

class WeatherOneCity: BaseFragment(), LocalContract.View{
    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private lateinit var city:String
    private val localPresenter by lazy{
        LocalWeatherPresenter(this)
    }

    companion object {
       const val TAG = "WeatherMain"
    }

    override fun initView() {
        city = arguments?.getString(ConstantValues.SELECT_ONE_CITY) ?: "北京"
        Log.i(TAG,"initView city = $city")
        localPresenter.loadWeather(city)
    }

    override fun lazyLoad() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_item
    }

    override fun showWeatherOnLocation(weather: WeatherData) {
        weather?.apply {
            tv_location?.text = city
            tv_temperature?.text = tmp
            tv_celsius?.visibility = View.VISIBLE
            weather_desc?.text = desc

            val nowTime = DateTime.now()
            val hourOfDay = nowTime.hourOfDay
            if (hourOfDay in 6..19) {
                iv_weather_type?.setImageResource(
                    IconUtils.getDayIconDark(cont_code)
                )
            }else{
                iv_weather_type?.setImageResource(
                    IconUtils.getNightIconDark(cont_code)
                )
            }
        }
    }

    override fun showErrorOnGetLocalWeather(city:String) {
        activity?.let {
            (activity as BaseActivity).showToast("未能获取到${city}的天气信息")
        }
    }

}