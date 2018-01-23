package com.ecng6613.point_of_sale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */



public class view_suppliers extends AppCompatActivity{
    Supplier_Adapter adapter;
    ArrayList<SupplierObject> ListViewSet;
    String it;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_suppliers_layout);
        ListView SuppliersList = (ListView)findViewById(R.id.view_sup_list);
        ListViewSet = new ArrayList<SupplierObject>();
        registerForContextMenu(SuppliersList);
        adapter = new Supplier_Adapter(this, ListViewSet);
        SuppliersList.setAdapter(adapter);
        new QueryAsync().execute("");
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            try {

                it = Query.getQuery(GetIP.LoadIP(view_suppliers.this), "Select * from supplier", "fetch");



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
                    ListViewSet.add(new SupplierObject(items[0], items[1], items[2], items[3]));
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
