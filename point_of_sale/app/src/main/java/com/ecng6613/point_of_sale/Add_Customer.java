package com.ecng6613.point_of_sale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class Add_Customer extends AppCompatActivity {

    EditText FirstName;
    EditText LastName;
    EditText Location;
    String FName;
    String LName;
    String Loc;
    String Date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer_layout);
        FirstName = (EditText)findViewById(R.id.add_cus_fname);
        LastName = (EditText)findViewById(R.id.add_cus_lname);
        Location = (EditText)findViewById(R.id.add_cus_loc);
        Button Save = (Button)findViewById(R.id.add_cus_save);
        setTitle("New Customer");
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new QueryAsync().execute("");
            }
        });

    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            Query.getQuery(GetIP.LoadIP(Add_Customer.this),"INSERT INTO `pos_db`.`customers` (`first_name`,`last_name`,`date_joined`,`location`) VALUES ('"+(FName)+"','"+LName+"','"+Date+"','"+Loc+"')","insert");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
           finish();


        }

        @Override
        protected void onPreExecute() {

            DateTime Now = new DateTime();
            FName = FirstName.getText().toString();
            LName = LastName.getText().toString();
            Loc = Location.getText().toString();
            Date =  Now.getYear() + "-" + Now.getMonthOfYear() + "-"+ Now.getDayOfMonth();

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
