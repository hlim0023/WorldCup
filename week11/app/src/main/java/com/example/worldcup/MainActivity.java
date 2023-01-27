package com.example.worldcup;

import static java.lang.Math.round;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcup.provider.MatchTimeAdapter;
import com.example.worldcup.provider.Result;
import com.example.worldcup.provider.ResultAdapter;
import com.example.worldcup.provider.ResultViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
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
    ResultAdapter adapter;
    TextView tv;
    int count;

    //week8
    DatabaseReference myRef,mCondition;

    // week 10

    String Week10_TAG = "Week10_TAG";
    int x1,y1;
    int hv_count = 0;
    //ArrayList<Integer> clicks = new ArrayList<>();

    //week 11
    private GestureDetectorCompat gestureDetector;// declare the engine
    private ScaleGestureDetector mScaleDetector;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(Week10_TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(Week10_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(Week10_TAG,"Action was UP");
                return true;
            default :
                return false;
        }
    }

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
        adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MatchTimeAdapter adapter_time = new MatchTimeAdapter();

        mResultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        mResultViewModel.getAllResults().observe(this, newData -> {
            adapter.setResult(newData);
            adapter_time.setResult(newData);
            adapter.notifyDataSetChanged();
            count = newData.size();
            tv.setText(count + "");
        });



        //    week8
        myRef = FirebaseDatabase.getInstance().getReference();


        View view=findViewById(R.id.view);

        //week 10
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                int x=(int)event.getX();
                int y=(int)event.getY();
                gestureDetector.onTouchEvent(event);
                mScaleDetector.onTouchEvent(event);

//                switch(action) {
//                    case (MotionEvent.ACTION_DOWN) :
//                        x1 = x;
//                        y1 = y;
//                        Log.d(Week10_TAG,"Action was DOWN at x="+x+ " and y="+y);
//                        return true;
//                    case (MotionEvent.ACTION_MOVE) :
//                        //Log.d(Week10_TAG,"Action was MOVE at x="+x+ " and y="+y);
//                        return true;
//                    case (MotionEvent.ACTION_UP) :
//                        Log.d(Week10_TAG,"Action was UP at x="+x+ " and y="+y);
//                        if ( Math.abs(x-x1) > 100 && Math.abs(y-y1) < 50  && Math.abs(x-x1) > Math.abs(y-y1)){
//                            if(x >x1){
//                                Log.d(Week10_TAG,"HHHHHHHH");
//                                addResult();
//                                hv_count++;
//                                Toast.makeText(getApplicationContext(), "Horizontal gestures +1 ; Total Gestures counts : "+hv_count, Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//
//                        if (Math.abs(y-y1) > 100 && Math.abs(x-x1) < 50 && Math.abs(x-x1) < Math.abs(y-y1) ){
//                            if (y > y1){
//                                clearButtonHandler();
//                                clearDatabase();
//                                mCondition.removeValue();
//                                Log.d(Week10_TAG,"VVVVVV");
//                                hv_count++;
//                                Toast.makeText(getApplicationContext(), "Vertical gestures +1 ; Total Gestures counts : "+hv_count, Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                                defaultTeam();
//                        }
//
////                        if (x == x1 && y == y1){//make sure it is not gestures
////                            Log.d(Week10_TAG, "SIZE " + clicks.size());
////                            int size = clicks.size();
////
////                            clicks.add(size,x);
////                            clicks.add((size+1),y);
////
////                            //check last 3 elements of the array list to check 3 clicks
////                            if(size > 6 && clicks.get(size-1) == clicks.get(size-3) && clicks.get(size-1) == clicks.get(size-5)
////                                    && clicks.get(size-2) == clicks.get(size-4) && clicks.get(size-2) == clicks.get(size-6)){
////                                String out = "The numbers of vertical and horizontal gestures is + " + hv_count;
////                                Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
////                                //System.out.println(out);
////                                Log.d(Week10_TAG,out);
////                                Log.d(Week10_TAG,"out");
////                            }
////
////                        }
//
//
//                        //Log.d(Week10_TAG,"COUNT " + hv_count);
//                        return true;
//                    default :
//                        return false;
//                }
                return true;
            }
        });

        //week 11
        /*
        * D -- Down
        * U -- UP
        * M -- Move
        * DUDU -- Double click
        * D-------U -- Scroll
        * DMMMMMMU --- Zoom in/out
        * */

        gestureDetector = new GestureDetectorCompat(this, new Week11GestureListener());
        mScaleDetector = new ScaleGestureDetector(this, new Week11ScaleGesture());


    }

    //week 10
    public void defaultTeam(){
        team1.setText("default_team1");
        team2.setText("default_team2");
        score1.setText("100");
        score2.setText("20");
        match.setText("0000");
        name_venue.setText("0");
        num_fans.setText("0");
    }



    @Override
    protected void onStart() {
        super.onStart();
        mCondition = myRef.child("week8/matches");
        mCondition.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //week6
//    public void addMatch() {
//        Match match = new Match(team1.getText().toString(),team2.getText().toString(),score1.getText().toString(),score2.getText().toString(), this.match.getText().toString(), name_venue.getText().toString(), num_fans.getText().toString());
//        //data.add(match);
//        //adapter.notifyDataSetChanged();
//
//    }


    //week7
    public void addResult() {
        Result result = new Result(team1.getText().toString(),team2.getText().toString(),score1.getText().toString(),score2.getText().toString(), this.match.getText().toString(), name_venue.getText().toString(), num_fans.getText().toString());
        mResultViewModel.insert(result);
        mCondition.push().setValue(result);
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

            for (int i=0 ; i<count; i++){
                mCondition.push().setValue(mResultViewModel.getAllResults().getValue().get(i));
            }
//            //week7
//            for(int i = 0; i < mResultViewModel.getAllResults().getValue().size(); i++){
//                Result current_result = mResultViewModel.getAllResults().getValue().get(i);
//                if (Integer.parseInt(current_result.getScore1()) == Integer.parseInt(current_result.getScore2()))
//                    mResultViewModel.getAllResults().getValue().remove(i);
//            }
//            adapter.notifyDataSetChanged();
//            tv.setText(mResultViewModel.getAllResults().getValue().size() + "");

            //week6
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
            mCondition.removeValue();
        }
        else if (id == R.id.database){
            setContentView(R.layout.database_view);
            RecyclerView recycle = findViewById(R.id.database_recyclerview);
            ResultAdapter _adapter = new ResultAdapter();
            recycle.setAdapter(_adapter);
            recycle.setLayoutManager(new LinearLayoutManager(this));

            RecyclerView recycle_time = findViewById(R.id.database_recycle_matchtime);
            MatchTimeAdapter _adapter_time = new MatchTimeAdapter();
            recycle_time.setAdapter(_adapter_time);
            recycle_time.setLayoutManager(new LinearLayoutManager(this));

            mResultViewModel.getAllResults().observe(this, newData -> {
                _adapter.setResult(newData);
                _adapter.notifyDataSetChanged();
                List<Result> temp_data=  new ArrayList<Result>(newData);
                for (int i =0; i < temp_data.size(); i++){
                    if (Integer.parseInt(temp_data.get(i).getVenue()) <= 5){
                        temp_data.remove(i);
                    }
                }
                _adapter_time.setResult(temp_data);

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

    class Week11GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("WEEK11", "DOUBLE TAP");
            defaultTeam();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            clearButtonHandler();
            Log.d("WEEK11", "Long Press");
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("WEEK11", "On Scroll, X: "+ distanceX +" , Y: "+distanceY);
            if(distanceX  < 0 ){//left to right
                int score = Integer.parseInt(String.valueOf(score1.getText()));
                Log.d("WEEK11", "On Scroll, score: "+ score);
                String text = String.valueOf(round(score + distanceX));
                if (round(score + distanceX) >0 )
                    score1.setText(text);
            }
            else{
                int score = Integer.parseInt(String.valueOf(score1.getText()));
                Log.d("WEEK11", "On Scroll, score: "+ score);
                String text = String.valueOf(round(score + distanceX));
                if (round(score + distanceX) < 1000 )
                    score1.setText(text);

            }
            return true;
        }



        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("WEEK11", "On Fling, X: "+ velocityX +" , Y: "+velocityY);
            moveTaskToBack(true);
            return true;
        }
    }

    class Week11ScaleGesture  extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Log.d("WEEK11", "ZOOM Begin");
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("WEEK11", "On Scale is moving ");
            return true;
        }
    }

}



