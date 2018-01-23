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

public class add_rewards extends AppCompatActivity {
    String RNameStr;
    String RPoints;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rewards_layout);
        final EditText RewardName = (EditText) findViewById(R.id.add_reward_name);
        final EditText RewardsPoints = (EditText) findViewById(R.id.add_reward_points);
        Button SaveRewards = (Button) findViewById(R.id.add_reward_save);
        setTitle("Add Rewards");
        SaveRewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RNameStr = RewardName.getText().toString();
                RPoints = RewardsPoints.getText().toString();
                new QueryAsync().execute("");
            }
        });
    }
    public class QueryAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            getQuery Query = new getQuery();
            Query.getQuery(GetIP.LoadIP(add_rewards.this),"INSERT INTO `pos_db`.`loyality_rewards_prizes` (`reward_name`,`points_required`) VALUES ('"+(RNameStr)+"','"+RPoints+"')","insert");
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


