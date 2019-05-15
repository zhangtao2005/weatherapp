package com.zht.weather.utils

/**
 *   author  :zhangtao
 *   date    :2019/5/9 18:05
 *   desc    :
 */
object ScheduleUtils {
    fun<T> ioToMain():IoMainScheduler<T>{
        return IoMainScheduler()
    }
}
