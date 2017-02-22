package com.ShoppingMall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 情v枫 on 2017/2/22.
 *
 *Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 上下文
     */
    public Context mContext;

    /**
     * 当fragment被创建
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    /**
     * 当系统调用该方法实例化视图的时候
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    /**
     * 由子类实现，实现特有的效果
     * @return
     */
    public abstract View initView() ;

    /**
     * 当子类需要
     * 1、把数据绑定到控件上的时候，重新该方法
     * 2、联网请求，把得到的数据绑定到视图上
     */
    public void initData() {

    }
}
