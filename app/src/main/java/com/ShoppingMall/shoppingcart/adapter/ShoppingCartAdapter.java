package com.ShoppingMall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ShoppingMall.R;
import com.ShoppingMall.home.bean.GoodsBean;
import com.ShoppingMall.shoppingcart.utils.CartStorage;
import com.ShoppingMall.shoppingcart.view.AddSubView;
import com.ShoppingMall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 情v枫 on 2017/2/28.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;


    public ShoppingCartAdapter(Context mContext, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.datas = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;
        showTotalPrice();

    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("总计：" + getTotalPrice());
    }

    //返回总价格
    private double getTotalPrice() {
        double totalPrice = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
                    totalPrice += Double.parseDouble(goodsBean.getCover_price()) * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    /**
     * 创建viewholder视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1、先得到数据
        final GoodsBean goodsBean = datas.get(position);
        //2、绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText(goodsBean.getCover_price());

        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());

        holder.addSubView.setMinValue(1);
        //设置库存--来自服务器
        holder.addSubView.setMaxValue(100);

        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                //1、回调数量
                goodsBean.setNumber(value);

                CartStorage.getInstance(mContext).updateData(goodsBean);

                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 检验是否全选
     */
    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isChecked()) {
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                } else {
                    //勾选
                    number++;
                }
            }
            if (datas.size() == number) {
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }
        } else {
            //没有数据
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    //删除数据
    public void deleteData(){
//        if(datas != null && datas.size()>0) {
//            for (int i=0;i<datas.size();i++){
//                GoodsBean goodsBean= datas.get(i);
//                if(goodsBean.isChecked()) {
//                    //1、内存中删除
//                    datas.remove(goodsBean);
//                    //2、本地也好保持
//                    CartStorage.getInstance(mContext).deleteData(goodsBean);
//                    //刷新数据
//                    notifyItemRemoved(i);
//                    i--;
//                }
//            }
//        }
        if(datas != null && datas.size()>0) {
            for (Iterator iterator = datas.iterator(); iterator.hasNext();){
                GoodsBean goodsBean = (GoodsBean) iterator.next();
                if(goodsBean.isChecked()) {
                    int position = datas.indexOf(goodsBean);
                    //从内存中移除
                    iterator.remove();
                    //本地也要同步
                    CartStorage.getInstance(mContext).deleteData(goodsBean);

                    //刷新页面
                    notifyItemRemoved(position);
                }
            }
        }
    }


    public void checkAll_none(boolean isChecked){
        if(datas != null &&datas.size()>0) {
            for (int i = 0;i<datas.size();i++){
                GoodsBean goodsBean = datas.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);

                //更新视图
                notifyItemChanged(i);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        AddSubView addSubView;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickListener(v,getLayoutPosition());
                    }
                }
            });
        }
    }

    //回调事件的监听
    private OnItemClickListener itemClickListener;

    //点击item监听
    public interface OnItemClickListener {
        public void onItemClickListener(View view, int position);
    }

    //设置item监听
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }
}
