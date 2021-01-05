package com.example.observ

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Flowable<T>.customObserver(success:(T)->Unit,error:(error:Throwable)->Unit) {
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :CustomObserver<T>(){
                override fun onNext(t: T) {
                        super.onNext(t)
                        Log.i("====", "onNext: "+t)
                        success(t)
                }

                override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.i("===", "onError: ")
                        error(t)
                }
        })
}
fun <T> Flowable<T>.customXMppObserver(before:()->Boolean,success:(T)->Unit,error:(error:Throwable)->Unit) {
        if(before()){
                this.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object :CustomObserver<T>(){
                                override fun onNext(t: T) {
                                        super.onNext(t)
                                        success(t)
                                }
                                override fun onError(t: Throwable) {
                                        super.onError(t)
                                        error(t)
                                }
                        })
        }else{
                error(Throwable("失败"))
        }
}