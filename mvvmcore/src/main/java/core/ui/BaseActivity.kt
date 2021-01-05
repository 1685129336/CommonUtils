package core.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import core.api.IActivity
import core.api.IView

abstract class BaseActivity : AppCompatActivity(),IActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID())
        initData()
        initView()
    }

    override fun startActivity(activity: Class<*>?) {
        val intent = Intent(this,activity)
        startActivity(intent)
    }

    override fun startActivity(activity: Class<*>?, data: Bundle?, paramName: String?) {
        val intent = Intent(this,activity)
        intent.putExtra(paramName,data)
        startActivity(intent)
    }

    override fun toast(msg: String?) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun toast(msg: String?, view: View?) {
        if (view != null && msg != null) {
            Snackbar.make(view,msg,Snackbar.LENGTH_LONG).show()
        }
    }
}