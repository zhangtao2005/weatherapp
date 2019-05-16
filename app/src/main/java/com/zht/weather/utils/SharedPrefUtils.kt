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

    private const val PREF_NAME = "default_pref"
    private var  mSharedPref:SharedPreferences

    init {
        mSharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun putStringIntoSet(key:String,value:String){
        var dataSet : MutableSet<String>? = getStringSet(key)
        if(dataSet == null){
            dataSet = mutableSetOf()
        }
        dataSet.add(value)
        mSharedPref.edit().putStringSet(key,dataSet).apply()
    }

    fun putStringIntoSetImmediate(key:String, value:String){
        var dataSet : MutableSet<String>? = getStringSet(key)
        if(dataSet == null){
            dataSet = mutableSetOf()
        }
        dataSet.add(value)
        mSharedPref.edit().putStringSet(key,dataSet).commit()
    }
    
    fun getStringSet(key:String): MutableSet<String> {
        val dataSet = mutableSetOf<String>()
        val oldSet = mSharedPref.getStringSet(key,null)
        oldSet?.let {
            oldSet.forEach { dataSet.add(it) }
        }
        return dataSet
    }
    
    

    
}