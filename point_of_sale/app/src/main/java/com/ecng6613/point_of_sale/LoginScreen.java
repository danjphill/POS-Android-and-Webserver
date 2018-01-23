package com.ecng6613.point_of_sale;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Daniel Phillips on 4/24/2017.
 */

public class LoginScreen extends AppCompatActivity {


    EditText Password;
    String StoredPassword;
    String StringID;
   public static Boolean LoggedIn = false;
    public static String LoginID = "null";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        final EditText Id = (EditText) findViewById(R.id.login_id_editText);
        Password  = (EditText) findViewById(R.id.login_password_editText);
        Button Login = (Button)findViewById(R.id.login_button);
        TextView IP = (TextView)findViewById(R.id.login_textView);
        setTitle("Login");
        IP.setText("Connected to " + GetIP.LoadIP(this));
        IP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,Set_IP.class );
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               StringID =  Id.getText().toString();
               new QueryAsync().execute("");
            }
        });

    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            StoredPassword = Query.getQuery(GetIP.LoadIP(LoginScreen.this),"select password_hash from employees where employee_id = "+ StringID,"fetch");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Query", StoredPassword);

            if (StoredPassword.equals(Password.getText().toString())){
                LoggedIn = true;
                LoginID = StringID;
                Intent intent = new Intent(LoginScreen.this,MainActivity.class);
                startActivity(intent);
               finish();
            }else{
                Toast.makeText(LoginScreen.this, "Invalid IP Password OR Employee ID",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    public static boolean getLogin(){
        return LoggedIn;
    }
    public static String getLoginID(){
        return LoginID;
    }
    public static void setLogin(Boolean status){
        LoggedIn = status;
    }
    public static void setLoginID(String ID){
        LoginID = ID;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
