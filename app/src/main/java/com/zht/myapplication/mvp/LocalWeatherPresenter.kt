package com.zht.myapplication.mvp

import com.zht.myapplication.model.WeatherModel
import com.zht.myapplication.net.ExceptionHandle
import io.reactivex.disposables.CompositeDisposable

class LocalWeatherPresenter: LocalContract.Presenter {
    private var mLocalView:LocalContract.View

    private val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val weatherModel by lazy { WeatherModel() }

    constructor(view:LocalContract.View){
        mLocalView = view
    }

    override fun loadWeather() {
        mCompositeDisposable.clear()
        weatherModel.getWeather("北京").subscribe({
            weather -> mLocalView.showWeatherOnLocation(weather)
        },{throwable ->
            ExceptionHandle.handleException(throwable)
        })
    }

    override fun addLocation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}