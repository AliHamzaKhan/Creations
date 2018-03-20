package com.example.aaa.campussystem.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aaa.campussystem.Adapters.JobAdapter;
import com.example.aaa.campussystem.ModelClass.CompanyPostAdd;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Jobsfragment extends Fragment {
View view;
FirebaseDatabase database;
DatabaseReference reference;
FirebaseAuth auth;
ListView list;
JobAdapter adapter;
StudentProfileClass student;
ArrayList<CompanyPostAdd> add;
String a;

    public Jobsfragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jobsfragment, container, false);

        viewCasting();

        if (add == null){
            add = new ArrayList<>();
        }

        a = auth.getCurrentUser().getUid();
        getJobs();
        adapter = new JobAdapter(getContext(),add,a);
        list.setAdapter(adapter);
        return view;
    }
//    public void getStudentProfile(){
//        a = auth.getCurrentUser().getUid();
//
//        DatabaseReference ref2 = reference.getRef().child("Students Lists").child(a);
//        ref2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                 student = dataSnapshot.getValue(StudentProfileClass.class);
//                 Log.d("Student",dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    public void getJobs(){

        DatabaseReference ref1 = reference.getRef();


        ref1.child("Jobs_Lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("jobs_fragment",dataSnapshot.getValue().toString());
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Log.d("", "onChildAdded: "+snapshot);
                    CompanyPostAdd job = snapshot.getValue(CompanyPostAdd.class);
                    add.add(job);
                    adapter.notifyDataSetChanged();
                }


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
    public void viewCasting(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
        list = view.findViewById(R.id.job_list);

    }
}
