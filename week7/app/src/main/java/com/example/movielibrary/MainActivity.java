package com.example.movielibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielibrary.provider.Result;
import com.example.movielibrary.provider.ResultAdapter;
import com.example.movielibrary.provider.ResultViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {


    EditText team1;
    EditText team2;
    EditText score1;
    EditText score2;
    EditText match;
    EditText name_venue;
    EditText num_fans;


    //     Week 5
//    private DrawerLayout drawerlayout;
//    private NavigationView navigationView;
    Toolbar toolbar;



    //week7
    private ResultViewModel mResultViewModel;
    TextView tv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_layout);

        team1 = findViewById(R.id.titletext);
        team2 = findViewById(R.id.yeartext);
        score1 = findViewById(R.id.countrytext);
        score2 = findViewById(R.id.genretext);
        match = findViewById(R.id.costtext);
        name_venue = findViewById(R.id.venue_main);
        num_fans = findViewById(R.id.fans_main);;


        //week4 Task 3
        //sms
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

        //Week 5
        toolbar = findViewById(R.id.toolbar);
        //build tool bar
        setSupportActionBar(toolbar);// the right icon

        //floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult();
            }
        });

        //week 6
//        recyclerView = findViewById(R.id.my_recycler_view);
//
//        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
//        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
//
//        adapter = new MyRecyclerAdapter();
//        adapter.setData(data);
//        recyclerView.setAdapter(adapter);

        //week7
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        tv = findViewById(R.id.count_id);
        ResultAdapter adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mResultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
            mResultViewModel.getAllResults().observe(this, newData -> {
                adapter.setResult(newData);
                adapter.notifyDataSetChanged();
                tv.setText(newData.size() + "");
            });


    }
    //week6
    public void addMatch() {
        Match match = new Match(team1.getText().toString(),team2.getText().toString(),score1.getText().toString(),score2.getText().toString(), this.match.getText().toString(), name_venue.getText().toString(), num_fans.getText().toString());
        //data.add(match);
        //adapter.notifyDataSetChanged();

    }
    //week7
    public void addResult() {
        Result result = new Result(team1.getText().toString(),team2.getText().toString(),score1.getText().toString(),score2.getText().toString(), this.match.getText().toString(), name_venue.getText().toString(), num_fans.getText().toString());
        mResultViewModel.insert(result);

    }



    //week5
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void clearButtonHandler(){
        team1.setText("");
        score2.setText("");
        team2.setText("");
        score1.setText("");
        match.setText("");
        name_venue.setText("");
        num_fans.setText("");
    }

    public void clearDatabase(){
        mResultViewModel.deleteAll();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, "The floating Fab get clicked "+ Integer.toString(fab_count) +" times", Toast.LENGTH_SHORT).show();
        //Snackbar.make(getCurrentFocus(), "Item added to list",Snackbar.LENGTH_LONG).show();
        int id=item.getItemId();
        if (id == R.id.clear) {
            //Toast.makeText(this, "Optional Menu clear fields", Toast.LENGTH_SHORT).show();
            clearButtonHandler();
        }
        else if (id == R.id.remove_last_menu_id) {
//            for(int i = 0; i < data.size(); i++){
//                //Log.i("week6", data.get(i).getScore1() +" " +data.get(i).getScore2());
//                if (Integer.parseInt(data.get(i).getScore1()) == Integer.parseInt(data.get(i).getScore2())){
//                    data.remove(i);
//                    //Log.i("week6", "true");
//                }
//
//            }

            //adapter.notifyDataSetChanged();
        }
        else if (id == R.id.clear_fields_menu_id){
            clearDatabase();
        }
        else if (id == R.id.database){
            setContentView(R.layout.database_view);
            RecyclerView recycle = findViewById(R.id.database_recyclerview);
            ResultAdapter _adapter = new ResultAdapter();
            recycle.setAdapter(_adapter);
            recycle.setLayoutManager(new LinearLayoutManager(this));

            mResultViewModel.getAllResults().observe(this, newData -> {
                _adapter.setResult(newData);
                _adapter.notifyDataSetChanged();
            });
        }
        return true;
    }



    //week4
    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */

            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

            StringTokenizer sT = new StringTokenizer(msg,";");

            String sms_title = sT.nextToken();
            String sms_year = sT.nextToken();
            String sms_country = sT.nextToken();
            String sms_genre = sT.nextToken();
            String sms_cost = sT.nextToken();
            String sms_venue = sT.nextToken();
            String sms_fans = sT.nextToken();

            team1.setText(sms_title);
            team2.setText(sms_year);
            score1.setText(sms_country);
            score2.setText(sms_genre);
            match.setText(sms_cost);
            name_venue.setText(sms_venue);
            num_fans.setText(sms_fans);
        }

    }

}



