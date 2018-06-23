package com.bway.day0623_demo.mvp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.day0623_demo.R;
import com.bway.day0623_demo.app.MyApp;
import com.bway.day0623_demo.mvp.model.bean.NewsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {

    private List<NewsBean.DataBeanX.DataBean> list;

    public NewsAdapter(List<NewsBean.DataBeanX.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_item01, parent, false);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_item02, parent, false);
        if (viewType == 0) {
            return new MyViewHolder01(view1);
        }
        return new MyViewHolder02(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);

        switch (itemViewType){
              case 0:
                  ((MyViewHolder01)holder).text01.setText(list.get(position).getTitle());
                  String p="http://365jia.cn/uploads/"+list.get(position).getPics().get(0);
                  ImageLoader.getInstance().displayImage(p,((MyViewHolder01) holder).img01, MyApp.getOptions());
                  ((MyViewHolder01)holder).img01.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          onItemClickListener.OnItemClick(v,position);
                      }
                  });
                  break;
            case 1:
                ((MyViewHolder02)holder).t1.setText(list.get(position).getTitle());
                String p1="http://365jia.cn/uploads/"+list.get(position).getPics().get(0);
                String p2="http://365jia.cn/uploads/"+list.get(position).getPics().get(1);
                String p3="http://365jia.cn/uploads/"+list.get(position).getPics().get(2);
                ImageLoader.getInstance().displayImage(p1,((MyViewHolder02) holder).img01, MyApp.getOptions());
                ImageLoader.getInstance().displayImage(p2,((MyViewHolder02) holder).img02, MyApp.getOptions());
                ImageLoader.getInstance().displayImage(p3,((MyViewHolder02) holder).img03, MyApp.getOptions());

                ((MyViewHolder02)holder).img01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.OnItemClick(v,position);
                    }
                });

                ((MyViewHolder02)holder).img02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.OnItemClick(v,position);
                    }
                });


                ((MyViewHolder02)holder).img03.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.OnItemClick(v,position);
                    }
                });
                break;
          }

          //长按删除
          holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View v) {
                  onItemLongClickListener.OnItemLongClick(v,position);
                  return false;
              }
          });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    @Override
    public int getItemViewType(int position) {
        List<String> pics = list.get(position).getPics();
        if(pics.size()<3){
            return 0;
        }
        return 1;
    }

    //创造方法，获取id
    public class MyViewHolder01 extends RecyclerView.ViewHolder {

        private final ImageView img01;
        private final TextView text01;

        public MyViewHolder01(View itemView) {
            super(itemView);
            img01 = itemView.findViewById(R.id.item01_img);
            text01 = itemView.findViewById(R.id.item01_text);

        }

    }

    public class MyViewHolder02 extends RecyclerView.ViewHolder {

        private final TextView t1;
        private final ImageView img01;
        private final ImageView img02;
        private final ImageView img03;

        public MyViewHolder02(View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.item02_text);
            img01 = itemView.findViewById(R.id.item02_img01);
            img02 = itemView.findViewById(R.id.item02_img02);
            img03 = itemView.findViewById(R.id.item02_img03);
        }
    }


  //设置接口，单击事件
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //设置接口  长按点击事件
    private   OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        void OnItemLongClick(View view,int position);
    }

public interface OnItemClickListener{
        void OnItemClick(View view,int postion);
}

}
