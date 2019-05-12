package com.zht.myapplication.mvp

interface BaseView<T> {

    fun setPresenter(presenter: T)

}