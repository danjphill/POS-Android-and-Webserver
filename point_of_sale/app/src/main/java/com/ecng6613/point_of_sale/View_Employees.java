package com.ecng6613.point_of_sale;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class View_Employees extends AppCompatActivity {
    ArrayList<EmployeeObject> ListViewSet;
    Employee_Adapter adapter;
    String it;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_employees_layout);
        ListView EmployeeList = (ListView)findViewById(R.id.viewEmployeesList);
        ListViewSet = new ArrayList<EmployeeObject>();
        registerForContextMenu(EmployeeList);
        adapter = new Employee_Adapter(this, ListViewSet);
        EmployeeList.setAdapter(adapter);
        setTitle("Employees");
        EmployeeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(View_Employees.this,Current_Profile.class);
                intent.putExtra("EmployeeID", adapter.getItem(i).getEmployee_id());
                startActivity(intent);
            }
        });
        new QueryAsync().execute("");

    }

    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            try {

                it = Query.getQuery(GetIP.LoadIP(View_Employees.this), "Select * from employees", "fetch");



                return "Executed";
            }catch(java.lang.StringIndexOutOfBoundsException e){
                e.printStackTrace();
                return "Failed";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            ListViewSet.clear();
            try {
                String[] lines = it.split(System.getProperty("line.separator"));
                for (int i = 0; i < lines.length; i++) {
                    String[] items = lines[i].split(",");
                    ListViewSet.add(new EmployeeObject(items[0], items[1], items[2], items[3], items[4], items[5], items[6],items[7],items[8]));
                }


            }catch (java.lang.ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}