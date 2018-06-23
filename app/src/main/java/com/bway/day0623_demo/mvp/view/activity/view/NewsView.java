package com.bway.day0623_demo.mvp.view.activity.view;

import com.bway.day0623_demo.base.IView;
import com.bway.day0623_demo.mvp.model.bean.NewsBean;

public interface NewsView extends IView{


    void onSuccess(NewsBean newsBean);
    void onFaild(String error);

}
