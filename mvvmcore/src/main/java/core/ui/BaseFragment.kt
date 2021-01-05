package core.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import core.api.IFragment

abstract class BaseFragment : Fragment(),IFragment {
    protected var mView:View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(layoutID(),container,false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initView()
    }

    override fun onResume() {
        super.onResume()
        //存放动画等，每次显示都要调用的
        initbusiness()
    }

    override fun onStart() {
        super.onStart()
        //回收资源等
        recycleData()
    }

    abstract fun recycleData()

    abstract fun initbusiness()
    override fun startActivity(activity: Class<*>?) {
        val intent = Intent(context,activity)
        startActivity(intent)
    }

    override fun startActivity(activity: Class<*>?, data: Bundle?, paramName: String?) {
        val intent = Intent(context,activity)
        intent.putExtra(paramName,data)
        startActivity(intent)
    }
    override fun toast(msg: String?) {
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
    }

    override fun toast(msg: String?, view: View?) {
        if (view != null && msg != null) {
            Snackbar.make(view,msg, Snackbar.LENGTH_LONG).show()
        }
    }
}