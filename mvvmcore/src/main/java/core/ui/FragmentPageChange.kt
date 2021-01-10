package core.ui

import android.os.Bundle
import core.api.FragmentPassByValue

fun FragmentPassByValue.startFragment(activity:BaseActivityPageChangeFragment, fragment: Class<*>, data: Bundle?){
    activity.startFragment(fragment, data)
}
fun FragmentPassByValue.startFragment(activity:BaseActivityPageChangeFragment, fragment: Class<*>, data: Bundle?, animIn:Int, animOut:Int){
    activity.startFragment(fragment, data,animIn, animOut)
}
fun FragmentPassByValue.callbackFragment(activity:BaseActivityPageChangeFragment):Boolean{
    return activity.callbackFragment()
}
fun FragmentPassByValue.callbackFragment(activity:BaseActivityPageChangeFragment, animIn: Int, animOut: Int):Boolean{
    return activity.callbackFragment(animIn, animOut)
}