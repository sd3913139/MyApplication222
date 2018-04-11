package com.example.j.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by j on 2018/1/23.
 */



public class ParaAdapter extends BaseAdapter {

    private List <ParaList> list;
    private LayoutInflater inflater;

    public  ParaAdapter(Context context, List<ParaList> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ParaList goods = (ParaList) this.getItem(position);

        ViewHolder viewholder;

        if(convertView == null){

            viewholder = new ViewHolder();

            convertView = inflater.inflate(R.layout.para_list_item, null);
            viewholder.para_id_a = (TextView) convertView.findViewById(R.id.text_id_a);
            viewholder.para_value_a = (TextView) convertView.findViewById(R.id.text_value_a);
            viewholder.para_id_b = (TextView) convertView.findViewById(R.id.text_id_b);
            viewholder.para_value_b = (TextView) convertView.findViewById(R.id.text_value_b);

            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder) convertView.getTag();
        }

        viewholder.para_id_a.setText(goods.getId_A());
        viewholder.para_id_a.setTextSize(13);
        viewholder.para_value_a.setText(goods.getparaValueA());
        viewholder.para_value_a.setTextSize(13);
        viewholder.para_id_b.setText(goods.getId_B());
        viewholder.para_id_b.setTextSize(13);
        viewholder.para_value_b.setText(goods.getparaValueB()+"");
        viewholder.para_value_b.setTextSize(13);

        return convertView;
    }

    public static class ViewHolder{
        public TextView para_id_a;
        public TextView para_value_a;
        public TextView para_id_b;
        public TextView para_value_b;
    }

}