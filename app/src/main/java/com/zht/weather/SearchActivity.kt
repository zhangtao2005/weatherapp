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
import com.zht.weather.base.BaseActivity
import com.zht.weather.model.CityBean
import com.zht.weather.mvp.SearchContract
import com.zht.weather.mvp.SearchPresenter
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.StatusBarUtil
import com.zht.weather.view.ViewAnimUtils
import com.zht.weather.view.recyclerview.SearchCityAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(),SearchContract.View<CityBean> {
    private var keyWords: String? = null

    override fun setPresenter(presenter: SearchContract.Presenter) {

    }

    override fun showSuccessOnGetCity(data: ArrayList<CityBean>) {
        val searchAdapter = SearchCityAdapter(this,data,R.layout.search_item)
        mCityRecycler.adapter = searchAdapter
    }

    override fun showErrorOnGetCity(city: String) {

    }

    private val searchPresenter:SearchContract.Presenter by lazy{
        SearchPresenter(this)
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

        mCityRecycler.layoutManager = LinearLayoutManager(this)

        et_search_view.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                closeSoftKeyboard()
                keyWords = et_search_view.text.toString().trim()
                Log.i(ConstantValues.TAG_SEARCH,"keyWords == null ? "+(keyWords.isNullOrEmpty()))
                if (keyWords.isNullOrEmpty()) {
                    showToast("请输入你感兴趣的城市")
                } else {
                    searchPresenter.search(et_search_view.text.toString().trim())
                }
            }
            return@setOnEditorActionListener false
         }

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
//        fab_circle.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary_dark))
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
//        val animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
//        animation.duration = 300
//        rel_container.startAnimation(animation)
        rel_container.visibility = View.VISIBLE
        //打开软键盘
        openKeyBord(et_search_view, applicationContext)
    }


    override fun start() {

    }

    // 返回事件
    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            fab_circle.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundColor))
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
    fun closeSoftKeyboard() {
        closeKeyBord(et_search_view, applicationContext)
    }
}
