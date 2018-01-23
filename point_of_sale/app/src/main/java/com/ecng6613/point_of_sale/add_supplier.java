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

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class add_supplier extends AppCompatActivity {
    String SupNameStr;
    String SupPhoneStr;
    String SupAddressStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_supplier_layout);
        final EditText SupplierName = (EditText)findViewById(R.id.add_supp_name);
        final EditText SupplierPhone = (EditText)findViewById(R.id.add_supp_number);
        final EditText SupplierAddress = (EditText)findViewById(R.id.add_supp_address);
        Button  SaveSupplier = (Button)findViewById(R.id.add_supp_save);
        setTitle("Add Supplier");
        SaveSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupNameStr = SupplierName.getText().toString();
                SupPhoneStr = SupplierPhone.getText().toString();
                SupAddressStr = SupplierAddress.getText().toString();
                new add_supplier.QueryAsync().execute("");
            }
        });
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            String Result = Query.getQuery(GetIP.LoadIP(add_supplier.this),"INSERT INTO `pos_db`.`supplier` (`supplier_name`,`supplier_number`,`supplier_address`) VALUES ('"+(SupNameStr)+"','"+SupPhoneStr+"','"+SupAddressStr+"');","insert");
            Log.d("Add Supplier",Result);
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
