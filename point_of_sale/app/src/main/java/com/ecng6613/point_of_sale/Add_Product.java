package com.ecng6613.point_of_sale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class Add_Product extends AppCompatActivity {

    String ProductNameStr;
    String PriceStr;
    String PriceUnitStr;
    String BarcodeStr;
    String QuantityStr;
    String MarginPercentageStr;
    String LocationStr;
    String ExpirationStr;
    String SupplierStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_layout);
        final EditText Product_Name = (EditText)findViewById(R.id.add_product_Name);
        final EditText Product_Price = (EditText)findViewById(R.id.add_product_Price);
        final EditText Product_PriceUnits = (EditText)findViewById(R.id.add_product_PriceUnits);
        final EditText Product_Barcode = (EditText)findViewById(R.id.add_product_Barcode);
        final EditText Product_Quantity = (EditText)findViewById(R.id.add_product_Quantity);
        final EditText Product_Margin_Percentage = (EditText)findViewById(R.id.add_product_MarginPercentage);
        final EditText Product_Location = (EditText)findViewById(R.id.add_product_Location);
        final EditText Product_Expiration = (EditText)findViewById(R.id.add_product_Expiration);
        final EditText Product_Supplier = (EditText)findViewById(R.id.add_product_Supplier);
        Button  Save = (Button)findViewById(R.id.add_product_save);
        setTitle("Add Product");
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductNameStr = Product_Name.getText().toString();
                PriceStr = Product_Price.getText().toString();
                PriceUnitStr = Product_PriceUnits.getText().toString();
                BarcodeStr = Product_Barcode.getText().toString();
                QuantityStr = Product_Quantity.getText().toString();
                MarginPercentageStr = Product_Margin_Percentage.getText().toString();
                LocationStr = Product_Location.getText().toString();
                ExpirationStr = Product_Expiration.getText().toString();
                SupplierStr = Product_Supplier.getText().toString();
                new QueryAsync().execute("");

            }

        });
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            Query.getQuery(GetIP.LoadIP(Add_Product.this),"INSERT INTO `pos_db`.`products` (`product_name`,`price`,`price_units`,`upc_barcode`,`quantity`,`margin_percentage`,`location_id`,`expiration_data`,`supplier_id`) VALUES ('"+ProductNameStr+"','"+PriceStr+"','"+PriceUnitStr+"','"+BarcodeStr+"','"+QuantityStr+"','"+MarginPercentageStr+"','"+LocationStr+"','"+ExpirationStr+"','"+SupplierStr+"')","insert");
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
