package com.bway.day0623_demo.base;

public abstract class BasePresenter<V extends IView> {


    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();

    //解决内存泄漏
    void onDestory() {
        view = null;
    }
}
