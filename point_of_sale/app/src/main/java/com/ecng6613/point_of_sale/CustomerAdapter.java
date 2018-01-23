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
 * Created by Daniel Phillips on 5/22/2017.
 */



    public class CustomerAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<CustomerObject> objects;


        private class ViewHolder {
            public TextView FirstName;
            public  TextView LastName;
            public  TextView Location;
            public ImageView Image;


        }

        public CustomerAdapter(Context context, ArrayList<CustomerObject> objects) {
            inflater = LayoutInflater.from(context);
            this.objects = objects;
        }


        @Override
        public CustomerObject getItem(int position) {
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
            com.ecng6613.point_of_sale.CustomerAdapter.ViewHolder holder = null;
            if (convertView == null) {
                holder = new CustomerAdapter.ViewHolder();
                convertView = inflater.inflate(
                        R.layout.customer_adapter, null);
                holder.FirstName = (TextView) convertView
                        .findViewById(R.id.cus_adap_fame);
                holder.LastName = (TextView) convertView
                        .findViewById(R.id.cus_adap_lame);
                holder.Location = (TextView) convertView
                        .findViewById(R.id.cus_adap_location);
                               holder.Image = (ImageView) convertView.findViewById(R.id.cus_adap_imageView);
                convertView.setTag(holder);
            } else {
                holder = (CustomerAdapter.ViewHolder) convertView.getTag();
            }

            holder.FirstName.setText("First Name: " + objects.get(position).getFirstName());
            holder.LastName.setText("Last Name: " + objects.get(position).getLastName());
            holder.Location.setText("Location: " + objects.get(position).getLocation());


            return convertView;
        }

    }
