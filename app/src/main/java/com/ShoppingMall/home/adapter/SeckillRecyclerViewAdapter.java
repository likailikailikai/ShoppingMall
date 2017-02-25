package com.ShoppingMall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ShoppingMall.R;
import com.ShoppingMall.home.bean.HomeBean;
import com.ShoppingMall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 情v枫 on 2017/2/25.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder>{
    private final Context mContext;
    private final List<HomeBean.ResultEntity.SeckillInfoEntity.ListEntity> datas;


    public SeckillRecyclerViewAdapter(Context mContext, HomeBean.ResultEntity.SeckillInfoEntity seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //根据位置得到数据
        HomeBean.ResultEntity.SeckillInfoEntity.ListEntity listEntity = datas.get(position);

        //绑定数据
        holder.tvCoverPrice.setText("￥"+listEntity.getCover_price());

        holder.tvOriginPrice.setText("￥"+listEntity.getOrigin_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listEntity.getFigure()).into(holder.ivFigure);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

         ViewHolder(final View view) {
            super(view);
             ButterKnife.inject(this,view);
             //设置每条的点击事件
             view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(listener != null) {
                         listener.onItemClick(view,getLayoutPosition());
                     }
                 }
             });
        }
    }
    //点击item 的接口
    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }
    private  OnItemClickListener listener;

    //设置item的点击事件
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
