package com.tzj.lajiche.rxstudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.mSpinner)
    Spinner mSpinner;
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    private int imgs[] = {R.drawable.ic_launcher_background, android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete, android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            , android.R.drawable.ic_delete
            ,
            android.R.drawable.ic_delete, android.R.drawable.ic_media_next, android.R.drawable.ic_dialog_map};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));


        for (int i = 0; i < 10; i++) {
            dataset.add("sadasdasd");
        }

        niceSpinner.setArrowTintColor(Color.BLACK);
        niceSpinner.attachDataSource(dataset);

        initData();
        //初始化并设置适配器
        initAdapter();
    }


    private void initAdapter() {
        /*
         * 参数：
         * 1. Context对象
         * 2. 设置要显示的数据源
         * 3. 控制每个item显示的布局文件
         * 4. 数据从哪来, String[]，数组中添加数据源的map中的所有key
         * 5. 数据到哪去，int[], 数组中添加参数3的布局文件中控件的id
         * 参数4+参数5的作用： 数据源会循环取出每一行的map对象
         * 让每一行的map对象根据参数4中的key获取value，将获取到的value显示到参数5对应的控件当中去
         * */
        adapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"img", "text"},
                new int[]{R.id.imageView1, R.id.textView1});

        mSpinner.setAdapter(adapter);
    }

    private void initData() {

        for (int i = 0; i < imgs.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("img", imgs[i]);
            map.put("text", "itemitem  " + i);
            list.add(map);
        }
    }
}
