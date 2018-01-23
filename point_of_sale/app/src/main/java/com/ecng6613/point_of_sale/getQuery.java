package com.ecng6613.point_of_sale;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Daniel Phillips on 4/22/2017.
 */

public class getQuery {
    public String getQuery(String IP, String Query) {

        // Do some validation here

        try {
            URL url = new URL("http://"+IP+"/db_query/"+Query);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("getQuery", "Connected");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
                Log.d("getQuery", "Disconnected");
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return "Not Found";
        }
    }
    public String getQuery(String IP, String Query, String Type) {

        // Do some validation here

        try {
            URL url = new URL("http://"+IP+"/db_query/"+Query+"/"+Type);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("getQuery", "Connected");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                if(stringBuilder.toString().length() > 1) {
                    return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
                }else{
                    return "";
                }
            } finally {
                urlConnection.disconnect();
                Log.d("getQuery", "Disconnected");
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return "Not Found";
        }
    }
}
