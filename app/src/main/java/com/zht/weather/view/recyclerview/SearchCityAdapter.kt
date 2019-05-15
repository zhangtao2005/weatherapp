package com.zht.weather.view.recyclerview

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.zht.weather.MainActivity
import com.zht.weather.MyApplication.Companion.context
import com.zht.weather.R
import com.zht.weather.model.CityBean
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.SharedPrefUtils

/**
 *   author  :zhangtao
 *   date    :2019/5/13 17:10
 *   desc    :
 */
class SearchCityAdapter(context: Context, cities:ArrayList<CityBean>, layoutId:Int) : BaseRecyclerAdapter<CityBean>(context,cities,layoutId) {
    override fun bindData(holder: ViewHolder, data: CityBean, position: Int) {
        val city:TextView = holder.getView(R.id.tv_city)
        city.text = data.cityName
        holder.setOnItemClickListener(View.OnClickListener {
            SharedPrefUtils.putStringIntoSet(key = ConstantValues.SELECT_CITIES,value = data.location!!)
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra(ConstantValues.SELECT_ONE_CITY,data.location)
            context.startActivity(intent)
        })
    }
}