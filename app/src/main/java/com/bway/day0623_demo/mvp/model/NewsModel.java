package com.bway.day0623_demo.mvp.model;

import android.util.Log;

import com.bway.day0623_demo.mvp.model.bean.NewsBean;
import com.bway.day0623_demo.utils.HttpConfig;
import com.bway.day0623_demo.utils.OkHttpUtils;
import com.google.gson.Gson;

public class NewsModel {

    private static final String TAG = "NewsModel************";
    //网络请求
    public void doNews(final onCallBack onCallBack) {

        String url = HttpConfig.URL_ONE;
        OkHttpUtils.getInsatnce().doGet(url, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {
                if (onCallBack != null) {
                    onCallBack.onFaild("异常");
                }
            }

            @Override
            public void onResonse(String json) {
                Log.d(TAG, "onResonse: "+json);
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(json, NewsBean.class);
                int code = newsBean.getCode();
                if (0==code) {
                    if (onCallBack != null) {
                        onCallBack.onSuccess(newsBean);
                    }
                } else {
                    if (onCallBack != null) {
                        onCallBack.onFaild("有异常");
                    }
                }

            }
        });


    }

    //设置接口
    public interface onCallBack {
        void onSuccess(NewsBean newsBean);

        void onFaild(String error);
    }
}
