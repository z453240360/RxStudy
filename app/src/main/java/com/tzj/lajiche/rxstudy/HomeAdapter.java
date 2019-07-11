package com.tzj.lajiche.rxstudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * 筛选页面列表适配器
 * 显示首字母和包含的比赛信息
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mDatas = new ArrayList<>();

    public HomeAdapter(Context context, List<String> datas) {
        this.mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_homelocation, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    mListener.onItemClick(position, holder.itemView);
//                }
//            }
//        });

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxt_clomn;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTxt_clomn = (TextView) itemView.findViewById(R.id.mTxt_clomn);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String status, String villageCode, String villageName);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    private OnImgClickListener onImgClickListener;

    public void setOnImgClickListener(OnImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
    }

    public interface OnImgClickListener {
        void onClick(int pos, String villageCode, String villageName);
    }

}
