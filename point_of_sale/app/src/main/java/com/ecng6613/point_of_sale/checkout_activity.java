package com.ecng6613.point_of_sale;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Phillips on 4/25/2017.
 */



public class checkout_activity extends AppCompatActivity{

    String StoredIP;
    static List<String> ListIDs = new ArrayList<String>();
    ArrayList<MainAdapterObject> CheckoutListViewSet;
    MainAdapter adapter;
    String result;
    ListView Checkout_List;
    String CustomerID;
    String ProfileInfo;
    TextView EmployeeName;
    TextView CheckoutTotal;
    Double Total = 0.0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        Checkout_List = (ListView)findViewById(R.id.checkout_list);
        Button Checkout_btn = (Button)findViewById(R.id.checkout_btn_checkout);
        ImageButton Checkout_btn_clear = (ImageButton)findViewById(R.id.checkout_btn_clear);
        ImageButton Checkout_btn_add = (ImageButton)findViewById(R.id.checkout_btn_add);
        CheckoutListViewSet = new ArrayList<MainAdapterObject>();
        adapter = new MainAdapter(this, CheckoutListViewSet);
        Checkout_List.setAdapter(adapter);
        StoredIP = GetIP.LoadIP(this);
        EmployeeName = (TextView)findViewById(R.id.checkbox_employee_name);
        CheckoutTotal = (TextView) findViewById(R.id.checkout_total);
        setTitle("Checkout");
        new QueryAsync().execute("");
        Checkout_btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutListViewSet.clear();
                adapter.notifyDataSetChanged();
            }
        });
        Checkout_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Create custom dialog object
                final Dialog dialog = new Dialog(checkout_activity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.checkout_layout_dialog);
                // Set dialog title
                dialog.setTitle("Custom Dialog");

                // set values for custom dialog components - text, image and button
                final TextView text = (TextView) dialog.findViewById(R.id.checkout_layout_dia_ID);
                Button checkout_btn = (Button) dialog.findViewById(R.id.checkout_layout_dia_button);


                dialog.show();


                // if decline button is clicked, close the custom dialog
                checkout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        CustomerID = text.getText().toString();
                        new QueryAsync_Checkout().execute("");
                        dialog.dismiss();
                    }
                });
            }
        });

        }




    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            ProfileInfo = Query.getQuery(StoredIP,"select * from employees where employee_id = "+ LoginScreen.getLoginID(),"fetch");
            try {
                for(int i = 0 ; i< ListIDs.size(); i++) {
                    result = Query.getQuery(StoredIP, "Select * from products where id=" +  ListIDs.get(i), "fetch");
                    String[] lines = result.split(System.getProperty("line.separator"));
                    for (int y = 0; y < lines.length; y++) {
                        String[] items = lines[y].split(",");
                        CheckoutListViewSet.add(new MainAdapterObject(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7], items[8], items[9], items[10], items[11]));
                        Total = Total + Float.parseFloat(items[2]);
                    }
                    //Log.d("Query", Query.getQuery(StoredIP, "Checkout - 1"));
                    Log.d("Query - Checkout 1", result);
                    Log.d("Query - Checkout 2", CheckoutListViewSet.size()+"");

                }
                return "Executed";
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "Failed";
            }
        }
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();
            String[] Items = ProfileInfo.split(",");
            EmployeeName.setText("Employee: " +Items[5] + " " + Items[6]);
            CheckoutTotal.setText("Total $ " + Total);

        }
    }
    public class QueryAsync_Checkout extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            try {
                for(int i = 0 ; i< CheckoutListViewSet.size(); i++) {

                    String Barcode = CheckoutListViewSet.get(i).getUpc_barcode();
                    DateTime currentTime = new DateTime();
                    String ParsedTime = + currentTime.getYear() + "-" + currentTime.getMonthOfYear() + "-" + currentTime.getDayOfMonth();

                    Query.getQuery(StoredIP, "INSERT INTO `pos_db`.`purchases` (`upc_barcode`,`customer_id`,`purchase_date`,`employee_id`) VALUES ('"+Barcode+"','"+CustomerID+"','"+ParsedTime+"','"+LoginScreen.getLoginID()+"');", "insert");
                    //Log.d("Query", Query.getQuery(StoredIP, "Checkout - 1"));
                    Log.d("Query - Checkout 3", ParsedTime);
                    Log.d("Query - Checkout 4", CheckoutListViewSet.size()+"");
                    CheckoutListViewSet.clear();
                    finish();
                }
                return "Executed";
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "Failed";
            }
        }
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();

        }
    }


    public static void add_item(String ID){
    ListIDs.add(ID);
    }
}
