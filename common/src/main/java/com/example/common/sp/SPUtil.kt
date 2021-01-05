package com.example.common.sp


import android.content.Context
import android.util.Log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SPUtil<T>(val context: Context, val name: String, val defaultValue: T) :
    ReadWriteProperty<Any, T> {

    private val sp by lazy { context.getSharedPreferences("1804a", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return findPreference(name,defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        inputPreference(name,value)
    }

     fun <T> findPreference(name: String, defaultValue: T): T {
        var result : Any
        result = when(defaultValue){
            is Long -> sp.getLong(name,defaultValue)
            is Float -> sp.getFloat(name,defaultValue)
            is Boolean -> sp.getBoolean(name,defaultValue)
            is String -> sp.getString(name,defaultValue)!!
            is Int -> sp.getInt(name,defaultValue)
            else ->{
                sp.getStringSet(name, mutableSetOf())!!
            }
        }
        return result as T
    }
     fun <T> inputPreference(name: String,value:T){
        with(sp.edit()){
            when(value){
                is Long -> putLong(name,value)
                is Float -> putFloat(name,value)
                is Boolean -> putBoolean(name,value)
                is String ->putString(name, value)!!
                is Int -> putInt(name,value)
                else ->{putStringSet(name,value as MutableSet<String>)}
            }
            commit()
        }

    }
}