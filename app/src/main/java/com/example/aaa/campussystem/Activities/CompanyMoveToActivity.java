package com.example.aaa.campussystem.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aaa.campussystem.Adapters.JobAdapter;
import com.example.aaa.campussystem.Adapters.JobAdapterListView;
import com.example.aaa.campussystem.ModelClass.CompanyPostAdd;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.view.View.GONE;

public class CompanyMoveToActivity extends AppCompatActivity {
String authToken;
ListView listView;
TextView q,w,e,r;
ImageButton back;
Spinner dots;
String name,faculty,phone,address,image;
FirebaseDatabase database;
DatabaseReference reference;
FirebaseAuth auth;
ArrayList<CompanyPostAdd> data;
JobAdapterListView adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_move_to);

        gettingIntentData();
        viewCasting();

        auth=FirebaseAuth.getInstance();



        if (data == null){
            data = new ArrayList<>();
        }

        gettingJobsLists(authToken);
        w.setText(faculty);
        q.setText(name);
        e.setText(phone);
        r.setText(address);
        adapter = new JobAdapterListView(getApplicationContext(),data);
        listView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void gettingIntentData(){
         name =  getIntent().getStringExtra("name");
         faculty =  getIntent().getStringExtra("faculty");
         phone =  getIntent().getStringExtra("phone");
         address =  getIntent().getStringExtra("address");
         image =  getIntent().getStringExtra("image");
        authToken = getIntent().getStringExtra("auth");
    }
    public void viewCasting(){
        listView = findViewById(R.id.list_view2);
        q = findViewById(R.id.one);
        w = findViewById(R.id.two);
        e = findViewById(R.id.three);
        r = findViewById(R.id.four);
        back = findViewById(R.id.back_button1);
        dots = findViewById(R.id.spinner);
        dots.setVisibility(GONE);
    }

    public void gettingJobsLists(String ab){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        DatabaseReference ref1 = reference.getRef();


        ref1.child("Jobs_Lists").child(authToken).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //  Log.d("TAGio",dataSnapshot.getValue().toString());

//                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
//                    Log.d("", "onChildAdded: "+snapshot);
//                    CompanyPostAdd job = snapshot.getValue(CompanyPostAdd.class);
//                    data.add(job);
//                    adapter.notifyDataSetChanged();
//                }
                CompanyPostAdd job = dataSnapshot.getValue(CompanyPostAdd.class);
                data.add(job);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
