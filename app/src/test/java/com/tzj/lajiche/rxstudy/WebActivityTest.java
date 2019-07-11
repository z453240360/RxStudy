package com.tzj.lajiche.rxstudy;

import android.util.Log;

import com.tzj.lajiche.rxstudy.http.IService;

import org.junit.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class WebActivityTest {

    @Test
    public void getClient() {
        System.out.print("ceshi");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn/")
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

                        return super.responseBodyConverter(type, annotations, retrofit);
                    }
                })
                .build();


        IService iService = retrofit.create(IService.class);

        Call<ResponseBody> login = iService.getLogin("http://japi.juhe.cn/joke/content/text.from?key=ae240f7fba620fc370b803566654949e&page=1&pagesize=10");

        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.print("onResponse: "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.print("onFailure: ");
            }
        });
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onKeyDown() {
    }

    @Test
    public void getClient1() {
    }
}