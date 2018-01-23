package com.ecng6613.point_of_sale;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Daniel Phillips on 4/24/2017.
 */

public class GetIP {

    public static String LoadIP(Context context ){
        SharedPreferences prefs = context.getSharedPreferences(
                "com.example.pos_app", Context.MODE_PRIVATE);
        String IPkey = "IP_Address";

// use a default value using new Date()
        return prefs.getString(IPkey, null);
    }
}
