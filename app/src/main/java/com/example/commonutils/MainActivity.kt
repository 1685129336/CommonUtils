package com.example.commonutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.net.RetrofitFactory
import com.example.observ.customObserver
import core.api.ErrorView
import core.ui.BaseLayout
import core.ui.DefaultErrorView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_01.setOnClickListener {
            base_layout.pageErrorState(ErrorView.NetState.NOT_NETWORK)
        }
        bt_02.setOnClickListener {
            base_layout.pageLoadingState()
        }
        bt_03.setOnClickListener {
            base_layout.pageNormalState()
            RetrofitFactory.create(NetApi::class.java).authCode.customObserver({
                Log.i("====", "验证码 -->${it.data}")
            },{
                Log.i("====", "错误信息${it.message}")
            })
        }

        
        base_layout.setOnBackClickListener {
            Toast.makeText(this, "返回页面", Toast.LENGTH_SHORT).show()
        }
        
        base_layout.setOnErrorClickListener {
            Toast.makeText(this, "页面重新加载", Toast.LENGTH_SHORT).show()
        }



    }
}