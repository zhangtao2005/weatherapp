package com.zht.weather.utils

import com.tencent.mmkv.MMKV


/**
 *   author  :zhangtao
 *   date    :2019/5/13 17:29
 *   desc    :
 */
object MMKVUtils {

    private const val DEFAULT_MMKV_NAME = "default_mmkv"
    private const val APPEND_DATA_SEPARATOR = "#"
    private var  mMMKV:MMKV

    init {
        mMMKV = MMKV.mmkvWithID(DEFAULT_MMKV_NAME)
    }

    fun putStringAppend(key:String, value:String){
        val listOld = getAppendStringAsList(key)
        listOld?.let {
            if (it.contains(value)) {
                return
            }
        }
        var dataAppended = getAppendString(key)
        var builder =
            if(dataAppended == null)
                StringBuilder()
            else
                StringBuilder(dataAppended)
        builder.append(value+APPEND_DATA_SEPARATOR)
        mMMKV.putString(key,builder.toString())
    }

    private fun getAppendString(key:String): String? {
        return mMMKV.getString(key,null)
    }

    fun getAppendStringAsList(key:String): List<String>? {
        val appendStr = getAppendString(key)
        return appendStr?.split(APPEND_DATA_SEPARATOR)?.filter { it.isNotEmpty() }
    }
    
    fun removeStringFromAppend(key:String,value:String){
        val appendString = getAppendString(key)
        val arrData = appendString?.split(APPEND_DATA_SEPARATOR)
        val mutableListData:MutableList<String>
        arrData?.let {
            mutableListData = arrData.toMutableList()
            val builder = StringBuilder()
            mutableListData.forEach {
                if(it != value)
                    builder.append(it+APPEND_DATA_SEPARATOR)
            }
            mMMKV.putString(key,builder.toString())
        }
    }

}