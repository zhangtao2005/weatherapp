package com.zht.weather.view.recyclerview

interface MultiType<in T> {
    fun getLayoutId(item: T , position: Int): Int
}
