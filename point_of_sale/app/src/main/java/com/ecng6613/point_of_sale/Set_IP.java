package com.ecng6613.point_of_sale;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Daniel Phillips on 4/22/2017.
 */

public class Set_IP extends AppCompatActivity {
    String it = "None";
    String IP;
    ProgressBar checking_bar;
    EditText  Ip_Address;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_ip);
        checking_bar = (ProgressBar)findViewById(R.id.set_ip_progressBar);
        Button connect_btn = (Button)findViewById(R.id.set_ip_connect);
        Ip_Address = (EditText)findViewById(R.id.set_ip_address);
        checking_bar.setVisibility(View.INVISIBLE);
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checking_bar.setVisibility(View.VISIBLE);
                new QueryAsync().execute("");
            }
        });

    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            it = Query.getQuery(IP,"test");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Query", it);
            checking_bar.setVisibility(View.INVISIBLE);
            if (it.contains("Connected")){
                setIP(IP);
                finish();
            }else{
                Toast.makeText(Set_IP.this, "Invalid IP Address",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            IP = Ip_Address.getText().toString();

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private void setIP(String IP){
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.pos_app", Context.MODE_PRIVATE);
        String IPkey = "IP_Address";
        prefs.edit().putString(IPkey, IP).apply();
    }
}
