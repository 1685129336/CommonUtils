package core.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import core.api.FragmentPassByValue

abstract class BaseActivityPageChangeFragment : BaseActivity() {
    var fragments = mutableListOf<Fragment>()
    var pageStack = mutableListOf<Fragment>()

    //下一页进入场动画
    var nextPageInAnimation: Int = 0
    var nextPageOutAnimation: Int = 0

    //上一页进入场动画
    var lastPageInAnimation: Int = 0
    var lastPageOutAnimation: Int = 0
    private var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun registerFragment(fragment: Fragment): Int {
        fragments.add(fragment)
        return count++
    }

    //页面切换
    protected fun pageChange(pos: Int, fs: MutableList<Fragment>, flag: Boolean, n1: Int, n2: Int, o1: Int, o2: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        if (flag) {
            transaction.setCustomAnimations(n1, n2)
        } else {
            transaction.setCustomAnimations(o1, o2)
        }
        if (pos < fs.size && pos >= 0 && fs.size > 0) {
            fs.forEach { if (it.isAdded) transaction.hide(it) }
            if (fs.get(pos).isAdded) {
                transaction.show(fs.get(pos))
            } else {
                transaction.add(frameLayoutId(), fs.get(pos))
            }
            if (flag) {
                pageStack.add(fragments.get(pos))
            }
            transaction.commit()
        }
    }

    //查询指定的fragment
    protected fun queryFragment(fragment: Class<*>, fragments: MutableList<Fragment>): Int {
        for (i in 0 until fragments.size) {
            val f = fragments.get(i)
            if (f::class.java == fragment && f is FragmentPassByValue) {
                return i
            }
        }
        return -1
    }

    fun startFragment(fragment: Class<*>, data: Bundle?) {
        startFragment(fragment, data, nextPageInAnimation, nextPageOutAnimation)
    }

    //切换fragment
    fun startFragment(fragment: Class<*>, data: Bundle?, animIn: Int, animOut: Int) {
        val queryFragment = queryFragment(fragment, fragments)
        val f = fragments.get(queryFragment) as FragmentPassByValue
        pageChange(queryFragment, fragments, true, animIn, animOut, lastPageInAnimation, lastPageOutAnimation)
        f.send(data)
    }

    fun callbackFragment(): Boolean {
        return callbackFragment(lastPageInAnimation, lastPageOutAnimation)
    }

    //提供外部调用
    fun callbackFragment(animIn: Int, animOut: Int): Boolean {
        if (pageStack.size > 1) {
            val removeAt = pageStack.removeAt(pageStack.size - 1)
            val queryFragment =
                    queryFragment(pageStack.get(pageStack.size - 1).javaClass, fragments)
            pageChange(queryFragment, fragments, false, nextPageInAnimation, nextPageOutAnimation, animIn, animOut)
            return true
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!callbackFragment(lastPageInAnimation, lastPageOutAnimation) &&
                event?.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
        }
        return false
    }

    abstract fun frameLayoutId(): Int

}