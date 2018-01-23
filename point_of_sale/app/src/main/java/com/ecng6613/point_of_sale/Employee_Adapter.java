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


    public class Employee_Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<EmployeeObject> objects;


    private class ViewHolder {
        public TextView Name;
        public TextView ID;
        public TextView EmplyedSince;
        public TextView Position;


    }

    public Employee_Adapter(Context context, ArrayList<EmployeeObject> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }


    @Override
    public EmployeeObject getItem(int position) {
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
        com.ecng6613.point_of_sale.Employee_Adapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new com.ecng6613.point_of_sale.Employee_Adapter.ViewHolder();
            convertView = inflater.inflate(
                    R.layout.employee_adapter_layout, null);
            holder.ID = (TextView) convertView
                    .findViewById(R.id.employee_adapter_ID);
            holder.Name = (TextView) convertView
                    .findViewById(R.id.employee_adapter_name);
            holder.Position = (TextView) convertView
                    .findViewById(R.id.employee_adapter_Position);
            holder.EmplyedSince = (TextView) convertView
                    .findViewById(R.id.employee_adapter_since);
            convertView.setTag(holder);
        } else {
            holder = (com.ecng6613.point_of_sale.Employee_Adapter.ViewHolder) convertView.getTag();
        }

        holder.ID.setText("ID: " + objects.get(position).getEmployee_id());
        holder.Name.setText("Name: " + objects.get(position).getFirst_name() + " " + objects.get(position).getLast_name());
        holder.Position.setText("Position: " + objects.get(position).getEmployee_position());
        holder.EmplyedSince.setText("Since: " + objects.get(position).getDate_employeed());

        return convertView;
    }
}

