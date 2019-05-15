package com.zht.weather.ui

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.NonNull
import androidx.core.app.ActivityOptionsCompat
import com.zht.weather.R
import com.zht.weather.SearchActivity
import com.zht.weather.WeatherData
import com.zht.weather.base.BaseFragment
import com.zht.weather.mvp.LocalContract
import com.zht.weather.mvp.LocalWeatherPresenter
import com.zht.weather.utils.IconUtils
import kotlinx.android.synthetic.main.fragment_main.*
import org.joda.time.DateTime

class LocalWeatherFragment: BaseFragment(), LocalContract.View{
    companion object {
       const val TAG = "LocalWeatherFragment"
    }
    override fun onAddClicked() {
        openSearchActivity()
    }

    private fun openSearchActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = activity?.let { ActivityOptionsCompat.makeSceneTransitionAnimation(it, iv_add, iv_add.transitionName) }
            startActivity(Intent(activity, SearchActivity::class.java), options?.toBundle())
        } else {
            startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    private var onAddClicked:View.OnClickListener = object:View.OnClickListener{
        override fun onClick(v: View?) {
            onAddClicked()
        }
    }

    override fun initView() {
        iv_add.setOnClickListener(onAddClicked)
    }

    override fun lazyLoad() {
        localPresenter = LocalWeatherPresenter(this)
        localPresenter.loadWeather()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun showWeatherOnLocation(weather: WeatherData) {
        weather.apply {
            tv_location?.text = city
            tv_temperature?.text = tmp
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

    private lateinit var localPresenter: LocalContract.Presenter

    override fun showErrorOnGetLocalWeather() {

    }

    override fun setPresenter(@NonNull presenter: LocalContract.Presenter) {
        localPresenter = checkNotNull(presenter)
    }
}