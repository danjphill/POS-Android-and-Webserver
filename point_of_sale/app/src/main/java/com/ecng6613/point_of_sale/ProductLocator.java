package com.ecng6613.point_of_sale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Daniel Phillips on 5/21/2017.
 */



public class ProductLocator extends AppCompatActivity{
    TextView ProductInfo;
    String ProductId;
    String ProductInfoStr;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_locator);
        ProductInfo = (TextView)findViewById(R.id.product_Locator_textView);
        Bundle extras = getIntent().getExtras();
        ProductId = extras.getString("ItemID");
        new QueryAsync().execute("");


    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            ProductInfoStr = Query.getQuery(GetIP.LoadIP(ProductLocator.this),"select * from products where id = "+ ProductId,"fetch");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Current Profile Query", ProductInfoStr);
            String[] Items = ProductInfoStr.split(",");
            ProductInfo.setText("  ID: " +Items[0] + " \n  Product Name :" + Items[1]+ " \n  Price :$" + Items[2]+ " \n  Price Unit :" + Items[3]+ " \n  UPC Barcode :" + Items[4]+ " \n  Quantity :" + Items[5]+ " \n  Number Sold :" + Items[6] +  " \n  Location ID :" + Items[9]+ " \n  Expiration Date :" + Items[10] + " \n  Supplier ID :" + Items[11]);





        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
