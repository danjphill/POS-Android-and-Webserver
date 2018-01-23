package com.ecng6613.point_of_sale;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Daniel Phillips on 4/24/2017.
 */

public class Current_Profile extends AppCompatActivity {

    TextView Name;
    TextView ID;
    TextView Employed_since;
    TextView LoggedInSince;
    String ProfileInfo;
    String StrID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_profile);
        Name  = (TextView)findViewById(R.id.profile_name);
        ID = (TextView)findViewById(R.id.profile_id);
        Employed_since = (TextView)findViewById(R.id.profile_start_date);
        LoggedInSince = (TextView)findViewById(R.id.profile_loggedin);
        try {
            Bundle extras = getIntent().getExtras();
            StrID = extras.getString("EmployeeID");
        }catch (NullPointerException e){
            StrID = LoginScreen.getLoginID();
        }
        new QueryAsync().execute("");
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            ProfileInfo = Query.getQuery(GetIP.LoadIP(Current_Profile.this),"select * from employees where employee_id = "+StrID ,"fetch");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Current Profile Query", ProfileInfo);

           String[] Items = ProfileInfo.split(",");
            Name.setText("Name: " +Items[5] + " " + Items[6]);
            ID.setText("Employee ID: "+ Items[0]);
            Employed_since.setText("Employed Since: " + Items[4]);

try {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    DateTime dt = formatter.parseDateTime(Items[3]);
    DateTime now = new DateTime();
    Duration duration = new Duration(dt, now);
    LoggedInSince.setText("Last Login: " + duration.getStandardHours() + " Hours Ago");
}catch(IllegalArgumentException e ){
    LoggedInSince.setText("Not Saved");
}


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
