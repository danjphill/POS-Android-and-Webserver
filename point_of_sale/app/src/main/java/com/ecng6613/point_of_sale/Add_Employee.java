package com.ecng6613.point_of_sale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.joda.time.DateTime;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class Add_Employee extends AppCompatActivity {
    String FNameStr;
    String LNameStr;
    String PasswordStr;
    String PositionStr;
    String DateStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee_layout);
        final EditText FName = (EditText)findViewById(R.id.add_employee_fname);
        final EditText LName  = (EditText)findViewById(R.id.add_employee_lname);
        final EditText Password = (EditText)findViewById(R.id.add_employee_pass);
        final EditText Position = (EditText)findViewById(R.id.add_employee_position);
        final EditText DateEmployeed = (EditText)findViewById(R.id.add_employee_date);
        Button Save = (Button)findViewById(R.id.add_employee_save);
        setTitle("New Employee");
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FNameStr=FName.getText().toString();
                LNameStr = LName.getText().toString();
                PasswordStr = Password.getText().toString();
                PositionStr = Position.getText().toString();
                DateStr = DateEmployeed.getText().toString();
                new QueryAsync().execute("");
            }
        });
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            Query.getQuery(GetIP.LoadIP(Add_Employee.this),"INSERT INTO `pos_db`.`employees` (`password_hash`,`date_employeed`,`first_name`,`last_name`,`employee_position`) VALUES ('"+PasswordStr+"','"+DateStr+"','"+FNameStr+"','"+LNameStr+"','"+PositionStr+"')","insert");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            finish();


        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
