package com.zht.weather

import android.Manifest
import android.content.Intent
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.zht.weather.base.BaseActivity
import com.zht.weather.ui.LocalWeatherFragment
import com.zht.weather.utils.ConstantValues
import com.zht.weather.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions



class MainActivity : BaseActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    
    private lateinit var contentFragment:Fragment
    override fun layoutId(): Int {
       return R.layout.activity_main
    }

    override fun initData() {
        val city:String? = intent.getStringExtra(ConstantValues.SELECT_ONE_CITY)
        city?.apply {
            Log.i(ConstantValues.TAG_SEARCH,"initData city = $city")

        }
    }

    override fun onNewIntent(intent: Intent?) {
        setIntent(intent)
        initData()
        super.onNewIntent(intent)
    }

    override fun initView() {
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, content)
        contentFragment = LocalWeatherFragment()
        supportFragmentManager.beginTransaction().replace(R.id.content,contentFragment).commit()
    }

    override fun start() {

    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission(){
        val perms = arrayOf(Manifest.permission.INTERNET)
        EasyPermissions.requestPermissions(this, "WeatherMVP应用需要以下权限，请允许", 0, *perms)

    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.INTERNET)) {

                }
            }
        }
    }

}
