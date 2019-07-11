package com.tzj.lajiche.rxstudy;


import android.Manifest;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mTxt)
    TextView mTxt;
    @BindView(R.id.mImg)
    ImageView mImg;
    private String[] strings;
    private List<String> mDatas;
    private List<TestBean> testBeans;
    private List<String> imgUrls;
    private Timer timer;
    private int count = 0;
    private String TAG = "dd";


    String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDatas();
        timer = new Timer();
        permission();
    }

    private void initDatas() {
        strings = new String[]{"天王盖地虎", "宝塔镇河妖", "脸红什么", "精神焕发", "怎么又黄啦", "防冷涂的蜡", "小鸡炖蘑菇"};
        mDatas = new ArrayList<>();
        mDatas.add("床前明月光");
        mDatas.add("疑是地上霜");
        mDatas.add("举头望明月");
        mDatas.add("低头思故乡");
        mDatas.add("天苍苍");
        mDatas.add("野茫茫");
        mDatas.add("我饿了");
        mDatas.add("要吃饭");

        testBeans = new ArrayList<>();
        testBeans.add(new TestBean("张三", 100));
        testBeans.add(new TestBean("李四", 100));
        testBeans.add(new TestBean("王二妈", 100));
        testBeans.add(new TestBean("周杰伦", 100));
        testBeans.add(new TestBean("蔡依林", 100));
        testBeans.add(new TestBean("韦小宝", 100));
        testBeans.add(new TestBean("雍正", 100));

        imgUrls = new ArrayList<>();
        imgUrls.add("http://img0.imgtn.bdimg.com/it/u=3206239474,605047352&fm=200&gp=0.jpg");
        imgUrls.add("http://img5.imgtn.bdimg.com/it/u=879314051,3416316916&fm=200&gp=0.jpg");
        imgUrls.add("http://img3.imgtn.bdimg.com/it/u=3666020964,2347504351&fm=27&gp=0.jpg");
        imgUrls.add("http://img1.imgtn.bdimg.com/it/u=2273845827,2289542918&fm=27&gp=0.jpg");
        imgUrls.add("http://img1.imgtn.bdimg.com/it/u=3371394957,3468073730&fm=27&gp=0.jpg");
    }


    @OnClick({R.id.mBtn_1, R.id.mBtn_2, R.id.mBtn_3, R.id.mBtn_4, R.id.mBtn_5, R.id.mBtn_6, R.id.mBtn_7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBtn_1:
                permission();
                break;
            case R.id.mBtn_2:
                startActivity(new Intent(this,Main2Activity.class));
                break;
            case R.id.mBtn_3:
                startActivity(new Intent(this,Main3Activity.class));
                break;
            case R.id.mBtn_4:

                upImg();



                break;
            case R.id.mBtn_5:
                break;
            case R.id.mBtn_6:

                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.i("dd", "onTick: " + (int) millisUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish() {
                        Log.i("dd", "onFinish: ");
                    }
                };
                countDownTimer.start();


                break;
            case R.id.mBtn_7:
                timer.cancel();
                break;
        }
    }

    private void upImg() {
        List<String> list = new ArrayList<>();
        list.add("/storage/emulated/0/Android/data/com.tzj.pro.tzj/cache/luban_disk_cache/153110182148134.jpg");
        list.add("/storage/emulated/0/Android/data/com.tzj.pro.tzj/cache/luban_disk_cache/1531101885708731.jpg");

        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
            parts.add(body);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.greenfortune.sh.cn/")
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> responseBodyCall = apiService.uploadManyImage(parts);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });

    }


    public void permission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(permissions)
                .subscribe(new io.reactivex.functions.Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.i(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.i(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.i(TAG, permission.name + " is denied.");
                        }
                    }
                });

    }

    public void getData(){

    }
}
