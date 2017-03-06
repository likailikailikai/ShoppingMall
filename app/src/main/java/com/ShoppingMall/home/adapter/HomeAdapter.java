package com.ShoppingMall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ShoppingMall.R;
import com.ShoppingMall.app.GoodsInfoActivity;
import com.ShoppingMall.app.WebViewActivity;
import com.ShoppingMall.home.activity.GoodsListActivity;
import com.ShoppingMall.home.bean.GoodsBean;
import com.ShoppingMall.home.bean.HomeBean;
import com.ShoppingMall.home.bean.WebViewBean;
import com.ShoppingMall.utils.Constants;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by 情v枫 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    public static final String WEBVIEW_BEAN = "webview_bean";
    public static String GOODS_BEAN = "goodsbean";
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
        return 6;
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
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, inflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //绑定数据
            viewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder viewHolder = (ChannelViewHolder) holder;
            //绑定数据
            viewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder viewHolder = (ActViewHolder) holder;
            viewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder viewHolder = (SeckillViewHolder) holder;
            viewHolder.setData(result.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder viewHolder = (RecommendViewHolder) holder;
            viewHolder.setData(result.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder viewHolder = (HotViewHolder) holder;
            viewHolder.setData(result.getHot_info());
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @InjectView(R.id.gv_channel)
        GridView gvChannel;
        ChannelAdapter channelAdapter;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.mContext = mContext;
        }

        public void setData(List<HomeBean.ResultEntity.ChannelInfoEntity> channel_info) {
            //设置Gridview的适配器
            channelAdapter = new ChannelAdapter(mContext, channel_info);
            gvChannel.setAdapter(channelAdapter);

            //设置item的点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    if (position < 9) {
                        Intent intent = new Intent(mContext, GoodsListActivity.class);
                        intent.putExtra("position", position);
                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<HomeBean.ResultEntity.BannerInfoEntity> banner_info) {
            //1、得到数据
            //2、设置Banner数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());

            }
            //简单使用
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(context).load(path).crossFade().into(imageView);
                        }
                    })
                    .start();

            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //3、设置banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    int realPosition = position;
                    if (realPosition < banner_info.size()) {
                        String product_id = "";
                        String name = "";
                        String cover_price = "";
                        String image = "";
                        if (realPosition == 0) {
                            product_id = "627";
                            cover_price = "32.00";
                            name = "剑三T恤批发";
                        } else if (realPosition == 1) {
                            product_id = "21";
                            cover_price = "8.00";
                            name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                        } else {
                            product_id = "1341";
                            cover_price = "50.00";
                            name = "【蓝诺】《天下吾双》 剑网3同人本";
                        }
                        image = banner_info.get(position).getImage();

                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setProduct_id(product_id);
                        goodsBean.setName(name);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setFigure(image);

//                        Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);

                        intent.putExtra(GOODS_BEAN, goodsBean);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.act_viewpager)
        ViewPager actViewpager;
        ViewPagerAdapter adapter;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final List<HomeBean.ResultEntity.ActInfoEntity> act_info) {
            //设置viewpager的适配器
            adapter = new ViewPagerAdapter(mContext, act_info);

            //美化viewpager库
            actViewpager.setPageMargin(20);//间距
            actViewpager.setOffscreenPageLimit(3);
            actViewpager.setAdapter(adapter);

            actViewpager.setPageTransformer(true, new RotateYTransformer());

            //设置点击事件
            adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
//                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    HomeBean.ResultEntity.ActInfoEntity actInfoEntity = act_info.get(position);

                    WebViewBean webViewBean = new WebViewBean();
                    webViewBean.setName(actInfoEntity.getName());
                    webViewBean.setUrl(actInfoEntity.getUrl());

                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra(WEBVIEW_BEAN, webViewBean);
                    mContext.startActivity(intent);
                }
            });


        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.countdownview)
        CountdownView countdownview;
        @InjectView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @InjectView(R.id.rv_seckill)
        RecyclerView rvSeckill;
        SeckillRecyclerViewAdapter adapter;

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final HomeBean.ResultEntity.SeckillInfoEntity seckill_info) {
            //1、设置RecycleView的适配器
            adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info);
            rvSeckill.setAdapter(adapter);

            //设置布局管理器
            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            //设置点击事件
            adapter.setOnItemClickListener(new SeckillRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
//                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    HomeBean.ResultEntity.SeckillInfoEntity.ListEntity infoBean = seckill_info.getList().get(position);
                    //商品新的bean对象
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(infoBean.getProduct_id());
                    goodsBean.setCover_price(infoBean.getCover_price());
                    goodsBean.setFigure(infoBean.getFigure());
                    goodsBean.setName(infoBean.getName());
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    mContext.startActivity(intent);

                }
            });
            //设置秒杀的事件
            countdownview.setTag("test1");
            long duration = Long.parseLong(seckill_info.getEnd_time()) - Long.parseLong(seckill_info.getStart_time());
            countdownview.start(duration);

        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @InjectView(R.id.gv_recommend)
        GridView gvRecommend;
        RecommendGridViewAdapter adaper;

        public RecommendViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(RecommendViewHolder.this, itemView);
        }

        public void setData(final List<HomeBean.ResultEntity.RecommendInfoEntity> recommend_info) {
            //1、设置适配器
            adaper = new RecommendGridViewAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(adaper);


            //设置点击事件
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "position==" + recommend_info.get(position).getName(), Toast.LENGTH_SHORT).show();

                    HomeBean.ResultEntity.RecommendInfoEntity infoEntity = recommend_info.get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(infoEntity.getName());
                    goodsBean.setCover_price(infoEntity.getCover_price());
                    goodsBean.setFigure(infoEntity.getFigure());
                    goodsBean.setProduct_id(infoEntity.getProduct_id());

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @InjectView(R.id.gv_hot)
        GridView gvHot;
        HotGridViewAdapter adapter;

        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final List<HomeBean.ResultEntity.HotInfoEntity> hot_info) {
            //设置适配器
            adapter = new HotGridViewAdapter(mContext, hot_info);
            gvHot.setAdapter(adapter);

            //设置item的点击事件
            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    HomeBean.ResultEntity.HotInfoEntity infoBean = hot_info.get(position);

                    //商品的新的对象
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(infoBean.getProduct_id());
                    goodsBean.setCover_price(infoBean.getCover_price());
                    goodsBean.setFigure(infoBean.getFigure());
                    goodsBean.setName(infoBean.getName());
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
