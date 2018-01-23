package com.ecng6613.point_of_sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniel Phillips on 4/24/2017.
 */

public class MainAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<MainAdapterObject> objects;


    private class ViewHolder {
        public TextView Item;
        public  TextView UPC;
        public  TextView Price;
        public  TextView Aisle;
        public ImageView Image;


    }

    public MainAdapter(Context context, ArrayList<MainAdapterObject> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }


    @Override
    public MainAdapterObject getItem(int position) {
        return objects.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return objects.size();
    }



    @Override
    public int getItemViewType(int position) {

        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(
                    R.layout.main_adapter, null);
            holder.Item = (TextView) convertView
                    .findViewById(R.id.textView3);
            holder.UPC = (TextView) convertView
                    .findViewById(R.id.textView4);
            holder.Price = (TextView) convertView
                    .findViewById(R.id.textView5);
            holder.Aisle = (TextView) convertView
                    .findViewById(R.id.textView6);
            holder.Image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Item.setText("Item: " + objects.get(position).getProduct_name());
        holder.UPC.setText("UPC: " + objects.get(position).getUpc_barcode());
        holder.Price.setText("Price: $" + objects.get(position).getPrice());
        holder.Aisle.setText("Aisle: " + objects.get(position).getLocation_id());

        return convertView;
    }

}
