package com.zht.weather

import android.annotation.TargetApi
import android.os.Build
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.*
import com.zht.weather.base.BaseActivity
import com.zht.weather.model.CityBean
import com.zht.weather.mvp.SearchContract
import com.zht.weather.mvp.SearchPresenter
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.StatusBarUtil
import com.zht.weather.view.ViewAnimUtils
import com.zht.weather.view.recyclerview.SearchCityAdapter
import com.zht.weather.view.recyclerview.SelectedCityAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(),SearchContract.View<CityBean> {
    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private val searchPresenter by lazy{
        SearchPresenter(this)
    }

    override fun showSelectedCities(data: ArrayList<String>) {

        val selectAdapter = SelectedCityAdapter(this,data,R.layout.select_item)
        mSelectedCityRecycler.adapter = selectAdapter

        val flexBoxLayoutManager = FlexboxLayoutManager(this)
        flexBoxLayoutManager.flexWrap = FlexWrap.WRAP      //按正常方向换行
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW   //主轴为水平方向，起点在左端
        flexBoxLayoutManager.alignItems = AlignItems.CENTER    //定义项目在副轴轴上如何对齐
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START  //多个轴对齐方式
        mSelectedCityRecycler.layoutManager = flexBoxLayoutManager

        mSearchCityRecycler.visibility = View.GONE
        mSelectedCityRecycler.visibility = View.VISIBLE
    }

    override fun showSuccessOnGetCity(data: ArrayList<CityBean>) {
        val searchAdapter = SearchCityAdapter(this,data,R.layout.search_item)
        mSearchCityRecycler.adapter = searchAdapter
        mSearchCityRecycler.layoutManager = LinearLayoutManager(this)
        mSearchCityRecycler.visibility = View.VISIBLE
        mSelectedCityRecycler.visibility = View.GONE
    }

    override fun showErrorOnGetCity(city: String) {
        showToast("未能查询到${city}的信息")
    }

    override fun layoutId(): Int {
       return R.layout.activity_search
    }

    override fun initData() {

    }

    override fun initView() {
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setUpEnterAnimation() // 入场动画
            setUpExitAnimation() // 退场动画
        } else {
            setUpView()
        }
        //取消
        tv_cancel.setOnClickListener { onBackPressed() }

        et_search_view.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                closeSoftKeyboard()
                val keyWords = et_search_view.text.toString().trim()
                Log.i(ConstantValues.TAG_SEARCH,"keyWords == null ? "+(keyWords.isNullOrEmpty()))
                if (keyWords.isNullOrEmpty()) {
                    showToast("请输入你感兴趣的城市")
                } else {
                    searchPresenter.search(et_search_view.text.toString().trim())
                }
            }
            return@setOnEditorActionListener false
         }

        searchPresenter.querySelectedCities()

    }

    /**
     * 进场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpEnterAnimation() {
        val transition = TransitionInflater.from(this)
            .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {

            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
    }

    /**
     * 退场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpExitAnimation() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 300
    }

    /**
     * 展示动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animateRevealShow() {
        ViewAnimUtils.animateRevealShow(
            this, rel_container,
            fab_circle.width / 2, R.color.design_default_color_primary_dark,
            object : ViewAnimUtils.OnRevealAnimationListener {
                override fun onRevealHide() {

                }

                override fun onRevealShow() {
                    setUpView()
                }
            })
    }

    private fun setUpView() {
        //打开软键盘
        openKeyBord(et_search_view, applicationContext)
    }


    override fun start() {

    }

    // 返回事件
    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimUtils.animateRevealHide(
                this, rel_container,
                fab_circle.width / 2, R.color.design_default_color_primary_dark,
                object : ViewAnimUtils.OnRevealAnimationListener {
                    override fun onRevealHide() {
                        defaultBackPressed()
                    }

                    override fun onRevealShow() {

                    }
                })
        } else {
            defaultBackPressed()
        }
    }

    // 默认回退
    private fun defaultBackPressed() {
        closeSoftKeyboard()
        super.onBackPressed()
    }

    /**
     * 关闭软件盘
     */
    private fun closeSoftKeyboard() {
        closeKeyBord(et_search_view, applicationContext)
    }
}
