package com.example.zh.babyplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZH on 2018/4/20.
 */

public class CheckListViewAdapter extends BaseAdapter{
    private List<CheckListViewItem> checkListViewItemList;
    private ModifyInterface modifyInterface;
    private Context context;
    public CheckListViewAdapter(Context context){
        this.context=context;
    }
    public void setCheckListViewItemList(List<CheckListViewItem> checkListViewItemList){
        this.checkListViewItemList=checkListViewItemList;
        notifyDataSetChanged();
    }
    public void setModifyInterface(ModifyInterface modifyInterface){
        this.modifyInterface=modifyInterface;
    }
    public int getCount(){return checkListViewItemList==null?0:checkListViewItemList.size();}
    public Object getItem(int position){return checkListViewItemList.get(position);}
    public long getItemId(int position){return position;}
    public View getView(final int position,View convertView,ViewGroup parent){
         final ViewHolder holder;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.center_check_listview_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        final CheckListViewItem checkListViewItem=checkListViewItemList.get(position);
        holder.date.setText(checkListViewItem.getDate());
        holder.height.setText(checkListViewItem.getHeight()+" cm");
        holder.weight.setText(checkListViewItem.getWeight()+" kg");
        if(checkListViewItem.getVaccine().equals("是")){
            holder.vaccine.setText("已注射疫苗");
        }else{
            holder.vaccine.setText("未注射疫苗");
        }
        return convertView;
    }
    class ViewHolder{
        TextView date,height,weight,vaccine;
        public ViewHolder(View itemView){
            date=(TextView) itemView.findViewById(R.id.check_time);
            height=(TextView) itemView.findViewById(R.id.check_height);
            weight=(TextView) itemView.findViewById(R.id.check_weight);
            vaccine=(TextView) itemView.findViewById(R.id.vaccine);
        }
    }
    public interface ModifyInterface{
        void childDelete(int position);
    }
}
