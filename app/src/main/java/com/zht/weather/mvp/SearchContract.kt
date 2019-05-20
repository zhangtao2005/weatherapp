package com.zht.weather.mvp

/**
 *   author  :zhangtao
 *   date    :2019/5/12 16:25
 *   desc    :
 */
interface SearchContract {
    interface Presenter: BasePresenter {
        fun search(city:String)
        fun querySelectedCities()
    }

    interface View<T>: BaseView {
        fun showSuccessOnGetCity(data:ArrayList<T>)
        fun showErrorOnGetCity(city:String)
        fun showSelectedCities(data:List<String>?)
    }
}