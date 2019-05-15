package com.zht.weather

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.RefWatcher
import com.zht.weather.utils.ConstantValues
import interfaces.heweather.com.interfacesmodule.view.HeConfig
import kotlin.properties.Delegates

/**
 *   author  :zhangtao
 *   date    :2019/5/9 18:49
 *   desc    :
 */
class MyApplication : Application() {

    private var refWatcher:RefWatcher ? = null

    companion object{
        const val TAG = "MyApplication"

        var context:Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context):RefWatcher?{
            val application = context.applicationContext as MyApplication
            return application.refWatcher
        }
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        initConfig()
        HeConfig.init(ConstantValues.HE_WERTHER_APP_ID, ConstantValues.HE_WEATHER_APP_KEY)
        HeConfig.switchToFreeServerNode()
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 隐藏线程信息 默认：显示
            .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag("run_as_one")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private val mActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }
}