package com.bway.day0623_demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity{

    protected  P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(proveId());
        //创建presenter
        presenter=provide();
        //初始化
        initViews();
        initData();
        initListener();
    }

    protected abstract int proveId();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initViews();

    protected abstract P provide();

    //解决内存泄漏

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
