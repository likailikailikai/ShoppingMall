package com.ShoppingMall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ShoppingMall.R;
import com.ShoppingMall.home.bean.TypeListBean;
import com.ShoppingMall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 情v枫 on 2017/3/6.
 * <p>
 * 作用：
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> {
    private final List<TypeListBean.ResultBean.PageDataBean> datas;
    private final Context mContext;
    private LayoutInflater inflater;

    public GoodsListAdapter(Context mContext, List<TypeListBean.ResultBean.PageDataBean> datas) {
        this.datas = datas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_goods_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.根据位置得到对应的数据
        TypeListBean.ResultBean.PageDataBean entity = datas.get(position);
        //2.绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + entity.getFigure()).placeholder(R.drawable.new_img_loading_2).into(holder.ivHot);
        holder.tvName.setText(entity.getName());
        holder.tvPrice.setText("￥" + entity.getCover_price());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClick(datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    /**
     * 点击item的接口
     */
    public interface OnItemClickListener{
        public void onItemClick(TypeListBean.ResultBean.PageDataBean data);
    }

    private OnItemClickListener listener;

    /**
     * 设置item的点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
