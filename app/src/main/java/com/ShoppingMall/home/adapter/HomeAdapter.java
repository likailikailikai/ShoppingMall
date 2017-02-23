package com.ShoppingMall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ShoppingMall.R;
import com.ShoppingMall.home.bean.HomeBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 情v枫 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final HomeBean.ResultEntity result;

    /**
     * 六种类型
     */
    /**
     * 横幅广告-要从0开
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private final LayoutInflater inflater;

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 根据位置得到相应的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }


    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public HomeAdapter(Context mContext, HomeBean.ResultEntity result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }


    /**
     * 当前的类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
        } else if (viewType == ACT) {
        } else if (viewType == SECKILL) {
        } else if (viewType == RECOMMEND) {
        } else if (viewType == HOT) {
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //绑定数据
            viewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
        } else if (getItemViewType(position) == ACT) {
        } else if (getItemViewType(position) == SECKILL) {
        } else if (getItemViewType(position) == RECOMMEND) {
        } else if (getItemViewType(position) == HOT) {
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private TextView tv_tilte;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_tilte = (TextView) itemView.findViewById(R.id.tv_tilte);
        }

        public void setData(List<HomeBean.ResultEntity.BannerInfoEntity> banner_info) {
            tv_tilte.setText("sssssssssssssssssssss");
        }
    }

}
