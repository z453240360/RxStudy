package com.tzj.lajiche.rxstudy;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public interface ApiService {

    //测试
    @POST()
    Call<ResponseBody> getCode(@Body RequestBody verifyCode);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadManyImage(@Part() List<MultipartBody.Part> file);//多张图片上传
}
