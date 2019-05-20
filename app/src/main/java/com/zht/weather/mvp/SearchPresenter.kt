package com.zht.weather.mvp

import android.text.TextUtils
import android.util.Log
import com.zht.weather.MyApplication
import com.zht.weather.model.CityBean
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.MMKVUtils
import interfaces.heweather.com.interfacesmodule.bean.Lang
import interfaces.heweather.com.interfacesmodule.bean.search.Search
import interfaces.heweather.com.interfacesmodule.view.HeWeather

/**
 *   author  :zhangtao
 *   date    :2019/5/12 16:30
 *   desc    :
 */
class SearchPresenter:SearchContract.Presenter {
    override fun querySelectedCities() {
        val citiesList = MMKVUtils.getAppendStringAsList(ConstantValues.SELECT_CITIES)
        mLocalView.showSelectedCities(citiesList)
    }

    private  var mLocalView:SearchContract.View<CityBean>

    constructor(searchView:SearchContract.View<CityBean>){
        mLocalView = searchView
    }

    override fun search(city: String) {
        HeWeather.getSearch(
            MyApplication.context,
            city,
            "cn",
            10,
            Lang.CHINESE_SIMPLIFIED,
            object : HeWeather.OnResultSearchBeansListener {
                override fun onError(throwable: Throwable) {
                    val search = Search()
                    search.status = "noData"
                    mLocalView.showErrorOnGetCity(city)
                }

                override fun onSuccess(search: Search) {
                    if (search.status != "unknown city" && search.status != "noData") {
                        val basic = search.basic
                        val data = ArrayList<CityBean>()

                        if (basic != null && basic.size > 0) {
                            if (data.size > 0) {
                                data.clear()
                            }
                            for (i in basic.indices) {
                                val basicData = basic[i]
                                basicData.apply {
                                    var parentCity = parent_city
                                    val adminArea = admin_area
                                    val cnty = cnty
                                    if (TextUtils.isEmpty(parentCity)) {
                                        parentCity = adminArea
                                    }
                                    if (TextUtils.isEmpty(adminArea)) {
                                        parentCity = cnty
                                    }
                                    val cityBean = CityBean("$parentCity - $location" ,cid,cnty,location = location,adminArea=adminArea)
                                    data.add(cityBean)
                                }
                            }
                            Log.i(ConstantValues.TAG_SEARCH,"data size = ${data.size}")
                            mLocalView.showSuccessOnGetCity(data)

                        }
                    }
                }
        })

    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }
}