package com.ecng6613.point_of_sale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private MobileServiceClient mClient;
    String it = null;
    SearchView searchView;
    boolean Search = false;
    String SQuery;

    String StoredIP;
    ArrayList<MainAdapterObject> ListViewSet;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");

        ListView MainList = (ListView) findViewById(R.id.MainListView);
        ListViewSet = new ArrayList<MainAdapterObject>();
        registerForContextMenu(MainList);
        adapter = new MainAdapter(this, ListViewSet);


        MainList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        MainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ProductLocator.class);
                intent.putExtra("ItemID", adapter.getItem(i).getId());
                startActivity(intent);
            }
        });

        StoredIP = GetIP.LoadIP(this);
        if(StoredIP == null){
            Intent intent = new Intent(this, Set_IP.class);
            startActivity(intent);
        }
        new QueryAsync().execute("");


//        try {
//            mClient = new MobileServiceClient(
//                    "https://possystem2.azurewebsites.net",
//                    this
//            );
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        final TodoItem item = new TodoItem();
//        item.Text = "Awesome item2";
//        mClient.getTable(TodoItem.class).insert(item, new TableOperationCallback<TodoItem>() {
//            public void onCompleted(TodoItem entity, Exception exception, ServiceFilterResponse response) {
//
//                if (exception == null) {
//                    // Insert succeeded
//                } else {
//                    Log.e("Failed","Failed");
//                }
//            }
//        });

    }
//    public List<String> dbConnect(String Host, String Port, String db_userid,
//                                  String db_password) {
//        List<String> Db_list = new ArrayList<String>();
//        try {
//            String ConnectionString = "jdbc:jtds:sqlserver://" + Host + ":"
//                    + Port;
//            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
//        Connection conn = DriverManager.getConnection(ConnectionString,
//                db_userid, db_password);
//
//
//            System.out.println("connected");
//        Statement statement = conn.createStatement();
//        String queryString = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'";
//        ResultSet rs = statement.executeQuery(queryString);
//            while (rs.next()) {
//            Log.d("dbitem",rs.getString(1));
//        }
//    } catch (Exception e) {
//        Db_list.add("Error");
//        e.printStackTrace();
//    }
//        return Db_list;
//}
//public class TodoItem {
//        public String Id;
//        public String Text;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem itemLogout = menu.findItem(R.id.action_Logout);
        MenuItem itemLogin = menu.findItem(R.id.action_Login);
        MenuItem itemProfile = menu.findItem(R.id.action_Profile);
        if(LoginScreen.getLogin()){
            itemLogin.setVisible(false);
            itemLogout.setVisible(true);
            itemProfile.setVisible(true);
        }else{
            itemLogin.setVisible(true);
            itemLogout.setVisible(false);
            itemProfile.setVisible(false);
        }
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search...");



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SQuery = newText;
                new QueryAsync().execute("");
                if (newText.length()>1) {
                    Search = true;
                }else{
                    Search = false;
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_Login:
                Intent intent = new Intent(MainActivity.this,LoginScreen.class);
                startActivity(intent);
                finish();
                break;
            // action with ID action_settings was selected
            case R.id.action_Logout:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT)
                        .show();
                LoginScreen.setLogin(false);
                LoginScreen.setLoginID(null);
                Intent intent2 = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.action_cart:
                Intent intent4 = new Intent(MainActivity.this,checkout_activity.class);
                startActivity(intent4);
                break;
            case R.id.action_Profile:
                Intent intent3 = new Intent(MainActivity.this,Current_Profile.class);
                startActivity(intent3);
                break;
            case R.id.action_View_Customer:
                Intent intent5 = new Intent(MainActivity.this,View_Customers.class);
                startActivity(intent5);
                break;
            case R.id.action_scan:
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.initiateScan();

                break;
            case R.id.action_Add_Rewards:
                Intent intent6 = new Intent(MainActivity.this,add_rewards.class);
                startActivity(intent6);
                break;
            case R.id.action_Add_Supplier:
                Intent intent7 = new Intent(MainActivity.this,add_supplier.class);
                startActivity(intent7);
                break;
            case R.id.action_View_Supplier:
                Intent intent8 = new Intent(MainActivity.this,view_suppliers.class);
                startActivity(intent8);
                break;
            case R.id.action_View_Employee:
                Intent intent10 = new Intent(MainActivity.this,View_Employees.class);
                startActivity(intent10);
                break;
            case R.id.action_Add_Employee:
                Intent intent9 = new Intent(MainActivity.this,Add_Employee.class);
                startActivity(intent9);
                break;

            case R.id.action_add_product:
                Intent intent11 = new Intent(MainActivity.this,Add_Product.class);
                startActivity(intent11);
                break;

            case R.id.action_search:

                break;
            default:
                break;
        }

        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_contextual_menu, menu);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            searchView.setQuery(result,true);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()){
            case R.id.main_context_addcart:
                Toast.makeText(this, this.adapter.getItem(info.position).getId(), Toast.LENGTH_SHORT)
                        .show();
                checkout_activity.add_item(this.adapter.getItem(info.position).getId());

        }
        return super.onContextItemSelected(item);
    }

    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            try {
                if (Search) {
                    it = Query.getQuery(StoredIP, "select * from products where (product_name like '%" + SQuery + "%') OR (upc_barcode like '%" + SQuery + "%')", "fetch");
                } else {
                    it = Query.getQuery(StoredIP, "Select * from products", "fetch");
                }
                Log.d("Query", Query.getQuery(StoredIP, "3"));

                return "Executed";
            }catch(java.lang.StringIndexOutOfBoundsException e){
                e.printStackTrace();
                return "Failed";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            ListViewSet.clear();
            try {
                String[] lines = it.split(System.getProperty("line.separator"));
                for (int i = 0; i < lines.length; i++) {
                    String[] items = lines[i].split(",");
                    ListViewSet.add(new MainAdapterObject(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7], items[8], items[9], items[10], items[11]));
                }


            }catch (java.lang.ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}





