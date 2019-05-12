package com.zht.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zht.myapplication.ui.LocalWeatherFragment
import com.zht.myapplication.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, content)
        supportFragmentManager.beginTransaction().replace(R.id.content,LocalWeatherFragment()).commit()
    }

}
