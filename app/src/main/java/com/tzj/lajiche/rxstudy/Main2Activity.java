package com.tzj.lajiche.rxstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tzj.lajiche.rxstudy.http.IService;
import com.tzj.lajiche.rxstudy.view.MagicCircle;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.mBtn_test1)
    Button mBtnTest1;
    @BindView(R.id.mBtn_test2)
    Button mBtnTest2;
    @BindView(R.id.mBtn_test3)
    Button mBtnTest3;
    @BindView(R.id.mBtn_test4)
    Button mBtnTest4;
    @BindView(R.id.mBtn_test5)
    Button mBtnTest5;
    @BindView(R.id.mBtn_test6)
    Button mBtnTest6;
    @BindView(R.id.mBtn_test7)
    Button mBtnTest7;
    @BindView(R.id.mCicle)
    MagicCircle mCicle;
    String TAG = "dd";
    @BindView(R.id.mEd_test)
    EditText mEdTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mBtn_test1, R.id.mBtn_test2, R.id.mBtn_test3, R.id.mBtn_test4, R.id.mBtn_test5, R.id.mBtn_test6, R.id.mBtn_test7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBtn_test1:
                mCicle.startAnimation();
                break;

            case R.id.mBtn_test2:
                test1();
                break;
            case R.id.mBtn_test3:
                getNet();
                break;
            case R.id.mBtn_test4:
                break;
            case R.id.mBtn_test5:
                break;
            case R.id.mBtn_test6:
                break;
            case R.id.mBtn_test7:
                break;
        }
    }

    public void getNet() {
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
                    Log.i(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }


    public void test1() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onNext("4");
                e.onComplete();
            }
        });


        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String o) {
                mEdTest.append(o+"\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
                mEdTest.setText("错误信息："+e);
            }

            @Override
            public void onComplete() {
                mEdTest.append("\n-----完成----");
            }
        };

        observable.subscribe(observer);
    }


    public void test2(){

        Observable<List<String>> observable = Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {

            }
        });


        observable.flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return null;
            }
        });


        Observer<List<Integer>> observer = new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> integers) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
