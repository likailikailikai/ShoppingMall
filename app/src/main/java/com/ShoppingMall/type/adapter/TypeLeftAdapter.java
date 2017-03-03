package com.ShoppingMall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ShoppingMall.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 情v枫 on 2017/3/3.
 * <p>
 * 作用：
 */

public class TypeLeftAdapter extends BaseAdapter {
    private final Context mContext;
    private final String[] datas;

    /**
     * 被点击的位置
     */
    private int selectPosition;

    public TypeLeftAdapter(Context mContext, String[] titles) {
        this.mContext = mContext;
        this.datas = titles;
    }

    @Override
    public int getCount() {
        return datas.length;
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
            convertView = View.inflate(mContext, R.layout.item_type, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置的到对应的数据
            viewHolder.tvTitle.setText(datas[position]);
        
        if(selectPosition == position) {
            //设置选中或者高亮
            //选中项背景
            viewHolder.tvTitle.setTextColor(Color.parseColor("#fd3f3f"));
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
        }else{
            //设置默认
            viewHolder.tvTitle.setTextColor(Color.parseColor("#323437"));
            convertView.setBackgroundResource(R.drawable.bg2);//其他背景项
        }

        return convertView;
    }

    /**
     * //接收传入的位置
     * @param position
     */
    public void changeSelected(int position) {
        this.selectPosition = position;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
