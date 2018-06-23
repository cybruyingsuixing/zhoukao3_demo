package com.bway.day0623_demo.mvp.presenter;


import android.util.Log;

import com.bway.day0623_demo.base.BasePresenter;
import com.bway.day0623_demo.mvp.model.NewsModel;
import com.bway.day0623_demo.mvp.model.bean.NewsBean;
import com.bway.day0623_demo.mvp.view.activity.view.NewsView;

public class NewsPresenter extends BasePresenter<NewsView>{

    private static final String TAG = "NewsPresenter**********";
    private NewsModel newsModel;

    public NewsPresenter(NewsView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        newsModel = new NewsModel();
    }

    public void getNews(){
        newsModel.doNews(new NewsModel.onCallBack() {
            @Override
            public void onSuccess(NewsBean newsBean) {
                if(view!=null){
                    Log.d(TAG, "onSuccess: "+newsBean.getCode());
                    view.onSuccess(newsBean);
                }
            }

            @Override
            public void onFaild(String error) {

                if(view!=null){
                    view.onFaild(error);
                }
            }
        });
    }

}
