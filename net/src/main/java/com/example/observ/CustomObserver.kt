package com.example.observ

import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscription

open class CustomObserver<T> : FlowableSubscriber<T> {
    override fun onSubscribe(s: Subscription) {
        s.request(Long.MAX_VALUE)
    }
    override fun onError(t: Throwable) {}
    override fun onComplete() {}
    override fun onNext(t: T) {}
}