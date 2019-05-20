package com.zht.weather

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.zht.weather.base.BaseActivity
import com.zht.weather.ui.WeatherMain
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

    }

    override fun onNewIntent(intent: Intent?) {
        Log.i(ConstantValues.TAG_MAIN,"enter onNewIntent")
        intent?.let {
            val oneCity = it.getStringExtra(ConstantValues.SELECT_ONE_CITY)
            oneCity?.let {
                contentFragment = WeatherMain()
                val args = Bundle()
                args.putString(ConstantValues.SELECT_ONE_CITY,oneCity)
                contentFragment.arguments = args
                supportFragmentManager.beginTransaction().replace(R.id.content,contentFragment).commitAllowingStateLoss()
            }
        }

        super.onNewIntent(intent)
    }

    override fun initView() {
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, content)
        contentFragment = WeatherMain()
        supportFragmentManager.beginTransaction().replace(R.id.content,contentFragment).commitAllowingStateLoss()
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
