package com.zht.myapplication.ui

import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import com.zht.myapplication.R
import com.zht.myapplication.Weather
import com.zht.myapplication.mvp.LocalContract
import com.zht.myapplication.mvp.LocalWeatherPresenter
import kotlinx.android.synthetic.main.fragment_main.*

class LocalWeatherFragment:BaseFragment(), LocalContract.View{
    companion object {
       const val TAG = "LocalWeatherFragment"
    }
    override fun onAddClicked() {

    }

    private lateinit var weather:Weather
    private var onAddClicked:View.OnClickListener = object:View.OnClickListener{
        override fun onClick(v: View?) {
            onAddClicked()
        }
    }

    override fun initView() {
        tv_add.setOnClickListener(onAddClicked)
    }

    override fun lazyLoad() {
        localPresenter = LocalWeatherPresenter(this)
        localPresenter.loadWeather()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun showWeatherOnLocation(weather: Weather) {
        this.weather = weather
        tv_location?.text = weather.data.city
        tv_temperature?.text = weather.data.wendu
        weather_desc?.text = weather.data.ganmao
        Log.i(TAG,"weather.data.yesterday.type = "+weather.data.yesterday.type)
        iv_weather_type?.setImageResource(
            when(weather.data.yesterday.type){
                "晴" -> R.drawable.sunny
                "小雨" -> R.drawable.rainny
                "雪" -> R.drawable.snow
                "多云" -> R.drawable.cloudy
                else -> R.drawable.sunny
            }
        )
    }

    private lateinit var localPresenter: LocalContract.Presenter

    override fun showErrorOnGetLocalWeather() {

    }

    override fun setPresenter(@NonNull presenter: LocalContract.Presenter) {
        localPresenter = checkNotNull(presenter)
    }
}