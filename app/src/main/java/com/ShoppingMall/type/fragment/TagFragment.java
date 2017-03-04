package com.ShoppingMall.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ShoppingMall.R;
import com.ShoppingMall.base.BaseFragment;
import com.ShoppingMall.type.adapter.TagGridViewAdapter;
import com.ShoppingMall.type.bean.TagBean;
import com.ShoppingMall.utils.Constants;
import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 情v枫 on 2017/3/3.
 * <p>
 * 作用：
 */

public class TagFragment extends BaseFragment {

    @InjectView(R.id.gv_tag)
    GridView gvTag;
    private List<TagBean.ResultEntity> result;
    private TagGridViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 1.把数据绑定到控件上的时候，重新该方法
     * 2.联网请求，把得到的数据绑定到视图上
     */
    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.TAG_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "TagFragment的数据联网成功了==");
                        processData(response);


                    }
                });
    }

    private void processData(String json) {

        TagBean tagBean = JSON.parseObject(json,TagBean.class);
        result = tagBean.getResult();
        if (result != null && result.size() > 0) {
            //设置适配器
            adapter = new TagGridViewAdapter(mContext, result);
            gvTag.setAdapter(adapter);


            //设置item的点击事件
            gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TagBean.ResultEntity resultEntity = result.get(position);
                    Toast.makeText(mContext, "" + resultEntity.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
