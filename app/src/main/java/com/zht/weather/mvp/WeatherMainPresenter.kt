package com.zht.weather.mvp

import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.MMKVUtils

/**
 *   author  :zhangtao
 *   date    :2019/5/16 17:41
 *   desc    :
 */
class WeatherMainPresenter:MainContract.Presenter {
    private var mView:MainContract.View

    constructor(view:MainContract.View){
        mView = view
    }

    override fun loadWeathers() {
        val list = MMKVUtils.getAppendStringAsList(ConstantValues.SELECT_CITIES)
        mView.onGetAllCityNames(list)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }
}