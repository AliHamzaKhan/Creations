package com.example.aaa.campussystem.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aaa.campussystem.Adapters.ApplyStudentListAdapter;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AppliedStudentsFragment extends Fragment {
View view;
FirebaseDatabase database;
DatabaseReference reference;
FirebaseAuth auth;
ApplyStudentListAdapter adapter;
ListView listView;
ArrayList<StudentProfileClass> studentsData;
String a;

    public AppliedStudentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_applied_students, container, false);
        viewCasting();
        if (studentsData == null){
            studentsData = new ArrayList<>();
        }
        a = auth.getCurrentUser().getUid();
        getAppliedStudents(a);

        adapter = new ApplyStudentListAdapter(getContext(),studentsData);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    public void getAppliedStudents(String a){
        DatabaseReference ref1 = reference.getRef();
        ref1.child("Applied_Students").child(a).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                StudentProfileClass students = dataSnapshot.getValue(StudentProfileClass.class);
                studentsData.add(students);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            StudentProfileClass profile = dataSnapshot.getValue(StudentProfileClass.class);
            for (StudentProfileClass students : studentsData){
                if (studentsData.contains(profile)){
                        studentsData.remove(students);
                    }
                }
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
        listView = view.findViewById(R.id.applied_list);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();

    }
}
