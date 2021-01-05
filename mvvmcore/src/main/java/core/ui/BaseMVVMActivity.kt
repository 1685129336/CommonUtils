package core.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import core.BaseViewModel
import core.api.IActivity

abstract class BaseMVVMActivity<VM : BaseViewModel<*,*,*>,V : ViewDataBinding> :AppCompatActivity(), IActivity {
    protected lateinit var viewModel:VM
    protected lateinit var binding:V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutID())
        viewModel = createViewModel()
        binding.setVariable(getVariable(),viewModel)
        initData()
        initView()
    }

    abstract fun getVariable(): Int
    abstract fun createViewModel(): VM
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
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }

    override fun toast(msg: String?, view: View?) {
        if (view != null && msg != null) {
            Snackbar.make(view,msg, Snackbar.LENGTH_LONG).show()
        }
    }
}