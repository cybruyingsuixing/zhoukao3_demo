package com.bway.day0623_demo.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bway.day0623_demo.R;
import com.bway.day0623_demo.base.BaseActivity;
import com.bway.day0623_demo.mvp.model.bean.NewsBean;
import com.bway.day0623_demo.mvp.presenter.NewsPresenter;
import com.bway.day0623_demo.mvp.view.activity.view.NewsView;
import com.bway.day0623_demo.mvp.view.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<NewsPresenter> implements View.OnClickListener,NewsView {

    private static final String TAG = "MainActivity//////////";

    private RecyclerView recyclerView;
    private List<NewsBean.DataBeanX.DataBean> list = new ArrayList<>();
    private NewsAdapter newsAdapter;

    @Override
    protected int proveId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
     presenter.getNews();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews() {

        //获取资源id
        recyclerView = findViewById(R.id.main_recyler);
    }

    @Override
    protected NewsPresenter provide() {
        return new NewsPresenter((NewsView) this);
    }

    @Override
    public void onClick(View v) {

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onSuccess(NewsBean newsBean) {
        Log.d(TAG, "onSuccess: "+newsBean);
        List<NewsBean.DataBeanX.DataBean> data = newsBean.getData().getData();
        list.addAll(data);
        //创建适配器
        newsAdapter = new NewsAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);

        //删除
        newsAdapter.setOnItemLongClickListener(new NewsAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, final int position) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除条目");
                builder.setMessage("确定要删除本宝宝吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        newsAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });

        //属性动画
          newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
              @Override
              public void OnItemClick(View view, int postion) {
                  Log.d(TAG, "OnItemClick: "+postion);
                  ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 360);
                   animator.setDuration(5000);
                   animator.start();

              }
          });

    }

    @Override
    public void onFaild(String error) {
        Log.d(TAG, "onFaild: "+error);
    }
}
