package com.zht.weather.utils

import android.content.Context
import android.content.SharedPreferences
import com.zht.weather.MyApplication.Companion.context

/**
 *   author  :zhangtao
 *   date    :2019/5/13 17:29
 *   desc    :
 */
object SharedPrefUtils {

    const val PREF_NAME = "default_pref"
    val mSharedPref:SharedPreferences

    init {
        mSharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun putStringIntoSet(key:String,value:String){
        var dataSet : HashSet<String>? = getStringSet(key) as HashSet<String>?
        if(dataSet == null){
            dataSet = HashSet()
        }
        dataSet.add(value)
        mSharedPref.edit().putStringSet(value,dataSet).apply()
    }

    fun putStringIntoSetImediate(key:String,value:String){
        var dataSet : HashSet<String>? = getStringSet(key) as HashSet<String>?
        if(dataSet == null){
            dataSet = HashSet()
        }
        dataSet.add(value)
        mSharedPref.edit().putStringSet(value,dataSet).commit()
    }
    
    fun getStringSet(key:String): MutableSet<String>? {
        return mSharedPref.getStringSet(key,HashSet<String>())
    }
    
    

    
}