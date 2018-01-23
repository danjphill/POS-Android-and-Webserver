package com.ecng6613.point_of_sale;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class View_Customers extends AppCompatActivity {
    ArrayList<CustomerObject> ListViewSet;
    CustomerAdapter adapter;
    String it;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customers_layout);
        ListView CustomerList = (ListView)findViewById(R.id.view_cus_list);
        ListViewSet = new ArrayList<CustomerObject>();
        setTitle("Customers");
        registerForContextMenu(CustomerList);
        adapter = new CustomerAdapter(this, ListViewSet);
        CustomerList.setAdapter(adapter);
        new QueryAsync().execute("");
        CustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(View_Customers.this,Customer_Info.class);
                intent.putExtra("CustomerID", adapter.getItem(i).getID());
                startActivity(intent);
            }
        });

    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            try {

                    it = Query.getQuery(GetIP.LoadIP(View_Customers.this), "Select * from customers", "fetch");



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
                    ListViewSet.add(new CustomerObject(items[0], items[1], items[2], items[3], items[4], items[5], items[6],items[7]));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_Add_Customer:
                Intent intent = new Intent(View_Customers.this, Add_Customer.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_customers_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
