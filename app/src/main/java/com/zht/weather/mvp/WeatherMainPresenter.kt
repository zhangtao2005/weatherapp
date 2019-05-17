package com.zht.weather.mvp

import android.util.Log
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.SharedPrefUtils

/**
 *   author  :zhangtao
 *   date    :2019/5/16 17:41
 *   desc    :
 */
class WeatherMainPresenter:MainContract.Presenter {
    private var mView:MainContract.View
    private var lastSize:Int = 0

    constructor(view:MainContract.View){
        mView = view
    }

    override fun loadWeathers() {
        val set = SharedPrefUtils.getStringSet(ConstantValues.SELECT_CITIES)
        Log.i(ConstantValues.TAG_MAIN,"set size = ${set.size}")
//        if(lastSize != set.size) {
            mView.onGetAllCityNames(set)
//        }
        lastSize = set.size
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }
}