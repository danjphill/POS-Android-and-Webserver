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


    public class Supplier_Adapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<SupplierObject> objects;


        private class ViewHolder {
            public TextView SupName;
            public  TextView SupID;
            public  TextView SupAddress;
            public  TextView SupPhone;



        }

        public Supplier_Adapter(Context context, ArrayList<SupplierObject> objects) {
            inflater = LayoutInflater.from(context);
            this.objects = objects;
        }


        @Override
        public SupplierObject getItem(int position) {
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
            com.ecng6613.point_of_sale.Supplier_Adapter.ViewHolder holder = null;
            if (convertView == null) {
                holder = new com.ecng6613.point_of_sale.Supplier_Adapter.ViewHolder();
                convertView = inflater.inflate(
                        R.layout.supplier_adapter_layout, null);
                holder.SupID = (TextView) convertView
                        .findViewById(R.id.Supplier_Adap_ID);
                holder.SupName = (TextView) convertView
                        .findViewById(R.id.Supplier_Adap_Name);
                holder.SupAddress = (TextView) convertView
                        .findViewById(R.id.Supplier_Adap_Address);
                holder.SupPhone = (TextView) convertView
                        .findViewById(R.id.Supplier_Adap_Phone);

                convertView.setTag(holder);
            } else {
                holder = (com.ecng6613.point_of_sale.Supplier_Adapter.ViewHolder) convertView.getTag();
            }

            holder.SupID.setText("Supplier ID: " + objects.get(position).getSupplierID());
            holder.SupName.setText("Name: " + objects.get(position).getSupplierName());
            holder.SupAddress.setText("Address: " + objects.get(position).getSupplierAddress());
            holder.SupPhone.setText("Phone: " + objects.get(position).getSupplierPhone());

            return convertView;
        }

    }

