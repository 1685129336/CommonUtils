package core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import core.api.FragmentPassByValue

fun FragmentPassByValue.startFragment(activity: FragmentActivity, fragment: Class<*>, data: Bundle?){
    if(activity is BaseActivityPageChangeFragment)
    activity.startFragment(fragment, data)
    else Throwable("跳转fragment activity必须是BaseActivityPageChangeFragment的子类")
}
fun FragmentPassByValue.startFragment(activity:FragmentActivity, fragment: Class<*>, data: Bundle?, animIn:Int, animOut:Int){
    if(activity is BaseActivityPageChangeFragment)
    activity.startFragment(fragment, data,animIn, animOut)
    else Throwable("跳转fragment activity必须是BaseActivityPageChangeFragment的子类")
}
fun FragmentPassByValue.callbackFragment(activity:FragmentActivity):Boolean{
   return if(activity is BaseActivityPageChangeFragment)
     activity.callbackFragment()
    else false
}
fun FragmentPassByValue.callbackFragment(activity:FragmentActivity, animIn: Int, animOut: Int):Boolean{
    return if(activity is BaseActivityPageChangeFragment)
    activity.callbackFragment(animIn, animOut)
    else false
}