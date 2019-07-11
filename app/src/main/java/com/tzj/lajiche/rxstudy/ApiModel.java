package com.tzj.lajiche.rxstudy;



import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MultipartBody;

/**
 * author：fmd on 16/9/12
 * use:
 */
public class ApiModel {

    private static final String TAG = "ApiModel";

    private static volatile ApiModel apiModel;

    public static ApiModel create() {
        if (apiModel == null) {
            synchronized (ApiModel.class) {
                if (apiModel == null) {
                    apiModel = new ApiModel();
                }
            }
        }
        return apiModel;
    }

    private ApiModel() {

    }



//    public Call upLoadImages(List<MultipartBody.Part> body) {
//        return RetroUtils.getApi().uploadManyImage(body).flatMap(FlatProvider.<BResponse>flat());
//    }

}
