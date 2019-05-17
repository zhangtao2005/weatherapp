package com.zht.weather.view.recyclerview

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayoutManager
import com.zht.weather.R
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.SharedPrefUtils

/**
 *   author  :zhangtao
 *   date    :2019/5/13 17:10
 *   desc    :
 */
class SelectedCityAdapter(context: Context, cities:ArrayList<String>, layoutId:Int) : BaseRecyclerAdapter<String>(context,cities,layoutId) {
    override fun bindData(holder: ViewHolder, cityData: String, position: Int) {
        holder.setText(R.id.tv_city,cityData)

        val params = holder.getView<TextView>(R.id.tv_city).layoutParams
        if(params is FlexboxLayoutManager.LayoutParams){
            params.flexGrow = 1.0f
        }
        val delete:ImageView = holder.getView(R.id.iv_delete)
        delete.setOnClickListener{
            removeOneCity(cityData,position)
        }
    }

    private fun removeOneCity(city:String,position: Int){
        datas?.remove(city)
        notifyItemChanged(position)
        val citiesSet = SharedPrefUtils.getStringSet(ConstantValues.SELECT_CITIES)
        if(citiesSet.remove(city)) {
            SharedPrefUtils.putStringSet(ConstantValues.SELECT_CITIES, citiesSet)
        }
    }
}