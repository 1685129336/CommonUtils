package com.example.converter;

import com.example.net.BaseEntity;
import com.example.net.BaseErrorEntity;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    CustomResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        try {
            BaseEntity entity = gson.fromJson(string, BaseEntity.class);
            if(entity.getCode()==0){
                return gson.fromJson(string,type);
            }else{
                BaseErrorEntity errorEntity = gson.fromJson(string, BaseErrorEntity.class);
                throw new ResultException("登录失败",errorEntity.getCode());
            }
        } finally {
            value.close();
        }
    }
}
