package com.example.commonutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.common.picture.MPictureUtil
import core.api.FragmentPassByValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    class MyFragment:Fragment(),FragmentPassByValue{
        override fun send(data: Bundle?) {

        }
    }
}