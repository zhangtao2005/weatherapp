package com.zht.weather.mvp

/**
 *   author  :zhangtao
 *   date    :2019/5/12 16:25
 *   desc    :
 */
interface SearchContract {
    interface Presenter: BasePresenter {
        fun search(city:String)
    }

    interface View<T>: BaseView<Presenter> {
        fun showSuccessOnGetCity(data:ArrayList<T>)
        fun showErrorOnGetCity(city:String)
    }
}