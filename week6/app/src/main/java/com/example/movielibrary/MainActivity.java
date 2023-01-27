package com.example.movielibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {


    EditText team1;
    EditText team2;
    EditText score1;
    EditText score2;
    EditText match;

    SharedPreferences sp;

    final String TITLE = "TITLE";
    final String YEAR = "YEAR";
    final String COUNTRY = "COUNTRY";
    final String GENRE = "GENRE";
    final String COST = "COST";
    final String SP_NAME ="data.dat";

    //     Week 5
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    Toolbar toolbar;

//    //ArrayList<String> listItems = new ArrayList<String>(); // list view information
//    ArrayAdapter<String> adapter;
//    private ListView myListView;

    private int fab_count = 0;

    public Context context = this;

    //week6
    ArrayList<Match> data = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_layout);


        team1 = findViewById(R.id.titletext);
        team2 = findViewById(R.id.yeartext);
        score1 = findViewById(R.id.countrytext);
        score2 = findViewById(R.id.genretext);
        match = findViewById(R.id.costtext);


        Log.i("week3", "onCreate");

        sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String title_name = sp.getString(TITLE, " ");

        Toast.makeText(getApplicationContext(), " Last Movie was "+ "' "+title_name + " ' "+ "", Toast.LENGTH_LONG).show();

        //week4 Task 3
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        /* Create and instantiate the local broadcast receiver
           This class listens to messages come from class SMSReceiver
         */
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        /*
         * Register the broadcast handler with the intent filter that is declared in
         * class SMSReceiver @line 11
         * */
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

        //Week 5
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        //build tool bar
        setSupportActionBar(toolbar);// the right icon

//        // for the list view
//        myListView =  findViewById(R.id.listView);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
//        myListView.setAdapter(adapter);


        //floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnClick( view);
                fab_count++;
                //Snackbar.make(view, "Item added to list",Snackbar.LENGTH_LONG).setAction("Undo", undoOnclickListener).show();
            }
        });

        //week 6
        recyclerView = findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager


        adapter = new MyRecyclerAdapter();
        adapter.setData(data);
        recyclerView.setAdapter(adapter);

    }
    //week6
    public void addMatch() {
        Match match = new Match(team1.getText().toString(),team2.getText().toString(),score1.getText().toString(),score2.getText().toString(), this.match.getText().toString());
        data.add(match);
        adapter.notifyDataSetChanged();

    }

    // the click foe the floating making the snack bar
    View.OnClickListener undoOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            data.remove(data.size()-1);
            adapter.notifyDataSetChanged();
        }
    };




    //week5
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void clearButtonHandler(){
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TITLE, " ");
        editor.putInt(YEAR, 0);
        editor.putString(COUNTRY, "");
        editor.putString(GENRE, "");
        editor.putString(COST, "");


        editor.apply();
        team1.setText(" ");
        score2.setText("");
        team2.setText(" ");
        score1.setText("");
        match.setText("");
        Log.i("week3","clear all");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "The floating Fab get clicked "+ Integer.toString(fab_count) +" times", Toast.LENGTH_SHORT).show();
        //Snackbar.make(getCurrentFocus(), "Item added to list",Snackbar.LENGTH_LONG).show();
        int id=item.getItemId();
        if (id == R.id.clear) {
            //Toast.makeText(this, "Optional Menu clear fields", Toast.LENGTH_SHORT).show();
            clearButtonHandler();
        }
        else if (id == R.id.remove_last_menu_id) {
            data.remove(data.size() -1);
            adapter.notifyDataSetChanged();
        }
        else if (id == R.id.clear_fields_menu_id){
            data.clear();
            adapter.notifyDataSetChanged();
        }
        return true;
    }



    public void handleOnClick(View view) {

        addMatch();

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

            team1.setText(sms_title);
            team2.setText(sms_year);
            score1.setText(sms_country);
            score2.setText(sms_genre);
            match.setText(sms_cost);
        }

    }

}



