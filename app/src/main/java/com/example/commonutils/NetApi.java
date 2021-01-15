package com.example.commonutils;

import com.example.net.BaseEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface NetApi {
    @GET("/microoffice/user/getAuthCode")
    Flowable<BaseEntity<Integer>> getAuthCode();
}
