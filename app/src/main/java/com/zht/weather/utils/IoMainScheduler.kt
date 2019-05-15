package com.zht.weather.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *   author  :zhangtao
 *   date    :2019/5/9 18:07
 *   desc    :
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(),AndroidSchedulers.mainThread())
