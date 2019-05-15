package com.zht.weather.mvp

interface BaseView<T> {

    fun setPresenter(presenter: T)

}