package com.ecng6613.point_of_sale;

import android.app.Dialog;
import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class Customer_Info extends AppCompatActivity {
    TextView Name;
    TextView ID;
    TextView Location;
    TextView MemberSince;
    TextView RewardsPoints;
    String ProfileInfo;
    String StrID;
    String Rewards;
    String[] RewardsSpinnerItems;
    Set<String> SpinnerSEt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customer_layout);
        Name = (TextView)(findViewById(R.id.view_cus_name));
        ID = (TextView)(findViewById(R.id.view_cus_id));
        Location = (TextView)(findViewById(R.id.view_cus_loc));
        MemberSince = (TextView)(findViewById(R.id.view_cus_since));
        RewardsPoints = (TextView)(findViewById(R.id.view_cus_rewardsp));
        Button Redeem = (Button)findViewById(R.id.view_cus_redeem);
        Bundle extras = getIntent().getExtras();
        StrID = extras.getString("CustomerID");
        SpinnerSEt = new TreeSet<String>();
        new QueryAsync().execute("");
        Redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetRewardsQueryAsync().execute("");
            }
        });
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            ProfileInfo = Query.getQuery(GetIP.LoadIP(Customer_Info.this),"select * from customers where customer_id = "+ StrID,"fetch");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Current Profile Query", ProfileInfo);

            String[] Items = ProfileInfo.split(",");
            Name.setText("Name: " +Items[1] + " " + Items[2]);
                MemberSince.setText("Member Since: "+ Items[3]);
                RewardsPoints.setText("Rewards Points: "+Items[4]);
                Location.setText("Location: "+Items[6]);
            ID.setText("Customer ID: "+ Items[0]);



        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    public class GetRewardsQueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            Rewards = Query.getQuery(GetIP.LoadIP(Customer_Info.this),"select * from loyality_rewards_prizes","fetch");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Current Rewards Query", Rewards);

            String[] Items = Rewards.split("\n");

            for (int i=0;i<Items.length;i++){
                String[] Split = Items[i].split(",");
                SpinnerSEt.add(Split[1]);
            }
            RewardsSpinnerItems = SpinnerSEt.toArray(new String[SpinnerSEt.size()]);
            final Dialog dialog = new Dialog(Customer_Info.this);
            // Include dialog.xml file
            dialog.setContentView(R.layout.redeem_layout_dialog);
            // Set dialog title
            dialog.setTitle("Custom Dialog");

            // set values for custom dialog components - text, image and button
            final Spinner redeem_spinner = (Spinner) dialog.findViewById(R.id.redeem_dialog_spinner);
            Button redeem_btn = (Button) dialog.findViewById(R.id.redeem_dialog_btn);
            ArrayAdapter aa = new ArrayAdapter(Customer_Info.this,android.R.layout.simple_spinner_item,RewardsSpinnerItems);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            redeem_spinner.setAdapter(aa);
            dialog.show();


            // if decline button is clicked, close the custom dialog
            redeem_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog

                    dialog.dismiss();
                }
            });


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
