package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.myapplication.R.layout.recyclerview_item;

/**
 * <pre>
 *     author : 27324
 *     e-mail : xxx@xx
 *     time   : 2019/03/22
 *     desc   :  //TODO
 *     version: 1.0
 * </pre>
 */
public class rec_item_Adapter extends RecyclerView.Adapter<rec_item_Adapter.MyViewHolder> implements View.OnClickListener {
    private List<HomeBean> list;
    private Context con;
    private LayoutInflater inflater;

    public rec_item_Adapter(List<HomeBean> list, Context con) {
        this.con = con;
        this.list = list;
        inflater = LayoutInflater.from(con);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(recyclerview_item,null);
        MyViewHolder myViewHolder =new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.mac.setText(list.get(position).getMac());
        holder.value.setText(list.get(position).getValue());
        holder.itemView.setTag(position);
        holder.mButton.setTag(position);
        if(list.get(position).getName().equals("温度"))
            holder.mimageView.setImageResource(R.drawable.temperature);
        else if (list.get(position).getName().equals("湿度"))
            holder.mimageView.setImageResource(R.drawable.humidity);
        else if (list.get(position).getName().equals("氧气"))
            holder.mimageView.setImageResource(R.drawable.oxygen);
        else if (list.get(position).getName().equals("一氧化碳"))
            holder.mimageView.setImageResource(R.drawable.co);
        else if (list.get(position).getName().equals("水表"))
            holder.mimageView.setImageResource(R.drawable.water);
        else if (list.get(position).getName().equals("电扇"))
            holder.mimageView.setImageResource(R.drawable.fan);
        else if (list.get(position).getName().equals("煤气"))
            holder.mimageView.setImageResource(R.drawable.gas);
    }
    //创建ViewHolder继承RecyclerView.MyViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView mac;
        private TextView value;
        private Button mButton;
        private ImageView mimageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            mac  = itemView.findViewById(R.id.Mac);
            value= itemView.findViewById(R.id.Value);
            mButton = itemView.findViewById(R.id.button);
            mimageView=itemView.findViewById(R.id.imageView);
            // 为ItemView添加点击事件
            itemView.setOnClickListener(rec_item_Adapter.this);
            mButton.setOnClickListener(rec_item_Adapter.this);
        }
    }
    //=======================以下为item中的button控件点击事件处理===================================

    //item里面有多个控件可以点击（item+item内部控件）
    @Override
    public int getItemCount() {
        return list.size();
    }
    public enum ViewName {
        ITEM,
        PRACTISE
    }

    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener  {
        void onItemClick(View v, ViewName viewName, int position);
    }
    private OnItemClickListener mOnItemClickListener;//声明自定义的接口

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }

    public void onClick(View v) {
        int position = (int) v.getTag();      //getTag()获取数据
        if (mOnItemClickListener != null) {
            switch (v.getId()){
                case R.id.recyclerView:
                    mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
                    break;
                default:
                    mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }

}
