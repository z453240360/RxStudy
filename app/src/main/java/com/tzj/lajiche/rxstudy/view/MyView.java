package com.tzj.lajiche.rxstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyView extends HorizontalScrollView{
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        final Canvas canvas = new Canvas();

        paint.setColor(Color.parseColor("#ff0000"));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(Color.parseColor("#90000000"));
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setHorizontalGravity(LinearLayout.HORIZONTAL);
        for (int i = 0; i < 10; i++) {
            final TextView textView = new TextView(context);
            textView.setText("为什么啊"+i);
            textView.setTextColor(Color.parseColor("#ff0000"));
            textView.setPadding(10,10,10,10);
            linearLayout.addView(textView);

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+textView.getText().toString(), Toast.LENGTH_SHORT).show();

                    int width1 = textView.getWidth();
                    int height1 = textView.getHeight();

                    int width = getWidth();
                    int height = getHeight();



                    smoothScrollTo(textView.getLeft() - (context.getResources().getDisplayMetrics().widthPixels / 2 - width1 / 2), 0);


                    drawCircle(canvas,paint,textView.getLeft() - (context.getResources().getDisplayMetrics().widthPixels / 2 - width1 / 2),0,50);
                }
            });
        }

        addView(linearLayout);
    }


    public void drawCircle(Canvas canvas,Paint paint,int x,int y,int r){
        canvas.drawCircle(x,y,r,paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
