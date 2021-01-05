package core.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import core.BaseViewModel
import core.api.IFragment

abstract class BaseMVVMFragment<VM : BaseViewModel<*,*,*>,V : ViewDataBinding> : Fragment(),IFragment {
    protected lateinit var viewModel:VM
    protected lateinit var binding:V

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var mView = inflater.inflate(layoutID(),null)
        binding = DataBindingUtil.bind(mView)!!
        viewModel = createViewModel()
        binding.setVariable(getVariable(),viewModel)
        return mView;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initView()
    }

    abstract fun getVariable(): Int

    abstract fun createViewModel(): VM
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