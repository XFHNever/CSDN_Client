package com.example.CSDNCLient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.CSDNCLient.R;
import com.nju.csdnclient.bean.CSDNUnit;

import java.util.List;

/**
 * Created by never on 2014/9/22.
 */
public class NewsAdaptor extends BaseAdapter{
    private LayoutInflater inflater;
    private List<CSDNUnit> datas;

    public NewsAdaptor(Context context, List<CSDNUnit> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<CSDNUnit> units) {
        datas.addAll(units);
    }

    public void setDatas(List<CSDNUnit> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_list_item, null);
            holder = new ViewHolder();
            holder.title_textview = (TextView) convertView.findViewById(R.id.item_title);
            holder.date_textview = (TextView) convertView.findViewById(R.id.item_date);
            holder.content_textview = (TextView) convertView.findViewById(R.id.item_content);
            holder.imageview = (ImageView) convertView.findViewById(R.id.item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CSDNUnit unit = datas.get(position);
        holder.title_textview.setText(unit.getTitle());
        holder.content_textview.setText(unit.getContent());
        holder.date_textview.setText(unit.getDate());

        if (unit.getImgPath() != null) {
            holder.imageview.setVisibility(View.VISIBLE);
            holder.imageview.setImageResource(R.drawable.detail_loading);
        } else {
            holder.imageview.setVisibility(View.GONE);
        }
        return convertView;
    }

    final class ViewHolder {
        TextView title_textview;
        TextView content_textview;
        ImageView imageview;
        TextView date_textview;
    }
}
