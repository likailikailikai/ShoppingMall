package com.ShoppingMall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

public class HotGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultEntity.HotInfoEntity> datas;

    public HotGridViewAdapter(Context mContext, List<HomeBean.ResultEntity.HotInfoEntity> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置获取数据
        HomeBean.ResultEntity.HotInfoEntity hotInfoEntity = datas.get(position);
        viewHolder.tvName.setText(hotInfoEntity.getName());
        viewHolder.tvPrice.setText("￥"+hotInfoEntity.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoEntity.getFigure()).into(viewHolder.ivHot);


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
