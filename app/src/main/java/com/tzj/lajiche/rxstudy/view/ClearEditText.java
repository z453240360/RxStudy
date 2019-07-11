package com.tzj.lajiche.rxstudy.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tzj.lajiche.rxstudy.R;

public class ClearEditText extends LinearLayout{

    private EditText mEd_text;
    private ImageView imageView;
    private CheckBox checkBox;

    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mEd_text = new EditText(context);
        mEd_text.setText("测试使用");
        imageView = new ImageView(context);
        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        checkBox = new CheckBox(context);


        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.addView(mEd_text);
        linearLayout.addView(imageView);
        linearLayout.addView(checkBox);
        linearLayout.setGravity(Gravity.CENTER);


        addView(linearLayout);

        if (mEd_text.getText().toString().length()>0){
            imageView.setVisibility(VISIBLE);
        }else {
            imageView.setVisibility(INVISIBLE);
        }


        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins();


        linearLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams1 = mEd_text.getLayoutParams();

        layoutParams.weight=1;
        mEd_text.setLayoutParams(layoutParams1);
        mEd_text.setText("");

        mEd_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==0){
                    imageView.setVisibility(INVISIBLE);
                }else {
                    imageView.setVisibility(VISIBLE);
                }
            }
        });



        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEd_text.setText("");
            }
        });
    }


}
