package com.tzj.lajiche.rxstudy.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IService {
    @GET
    Call<ResponseBody> getLogin (@Url String url);
}
