package com.zht.weather.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.zht.weather.R
import com.zht.weather.SearchActivity
import com.zht.weather.base.BaseFragment
import com.zht.weather.mvp.MainContract
import com.zht.weather.mvp.WeatherMainPresenter
import com.zht.weather.utils.ConstantValues
import kotlinx.android.synthetic.main.fragment_main.*

class WeatherMain: BaseFragment(), MainContract.View{

    override fun onGetAllCityNames(set: MutableSet<String>) {
        val fragmentItems = FragmentPagerItems(activity)
        var fragmentPagerItem:FragmentPagerItem
        var bundle:Bundle
        set.forEach {
            bundle = Bundle()
            bundle.putString(ConstantValues.SELECT_ONE_CITY,it)
            fragmentPagerItem = FragmentPagerItem.of(it,WeatherOneCity::class.java,bundle)
            fragmentItems.add(fragmentPagerItem)
        }
        val fragmentPageAdapter = FragmentPagerItemAdapter(fragmentManager,
            fragmentItems)
        viewpager.adapter = fragmentPageAdapter
        viewpagertab.setViewPager(viewpager)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {

    }

    override fun onTabSelected(index: Int) {

    }

    companion object {
       const val TAG = "WeatherMain"
    }
    override fun onAddClicked() {
        openSearchActivity()
    }

    private fun openSearchActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = activity?.let { ActivityOptionsCompat.makeSceneTransitionAnimation(it, iv_add, iv_add.transitionName) }
            startActivity(Intent(activity, SearchActivity::class.java), options?.toBundle())
        } else {
            startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    private var onAddClicked:View.OnClickListener = View.OnClickListener {
        onAddClicked()
    }

    override fun initView() {
        iv_add.setOnClickListener(onAddClicked)
    }

    override fun lazyLoad() {
        localPresenter = WeatherMainPresenter(this)
        localPresenter.loadWeathers()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    private lateinit var localPresenter: MainContract.Presenter

}