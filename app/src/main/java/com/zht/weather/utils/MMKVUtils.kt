package com.zht.weather.utils

import com.tencent.mmkv.MMKV

/**
 *   author  :zhangtao
 *   date    :2019/5/13 17:29
 *   desc    :
 */
object MMKVUtils {

    private const val DEFAULT_MMKV_NAME = "default_mmkv"
    private var  mMMKV:MMKV

    init {
        mMMKV = MMKV.mmkvWithID(DEFAULT_MMKV_NAME)
    }

    fun putStringIntoSet(key:String,value:String){
        var dataSet : MutableSet<String>? = getStringSet(key)
        if(dataSet == null){
            dataSet = mutableSetOf()
        }
        dataSet.add(value)
        mMMKV.putStringSet(key,dataSet)
    }

    fun getStringSet(key:String): MutableSet<String> {
        val dataSet = mutableSetOf<String>()
        val oldSet = mMMKV.getStringSet(key,null)
        oldSet?.let {
            oldSet.forEach { dataSet.add(it) }
        }
        return dataSet
    }
    
    fun putStringSet(key:String,set:MutableSet<String>){
        mMMKV.putStringSet(key,set)
    }

}