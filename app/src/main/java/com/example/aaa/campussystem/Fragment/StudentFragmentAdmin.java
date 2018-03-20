package com.example.aaa.campussystem.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aaa.campussystem.Adapters.StudentListAdapter;
import com.example.aaa.campussystem.ModelClass.SignUpClass;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class StudentFragmentAdmin extends Fragment {
View view;
FirebaseDatabase database;
DatabaseReference reference;
//DatabaseReference studentsRef;
FirebaseAuth auth;
ListView listView;
StudentListAdapter adapter;
ArrayList<StudentProfileClass> data;


    public StudentFragmentAdmin() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_fragment_admin, container, false);
        castingVariables();
//
        if (data == null){
            data = new ArrayList<>();
        }

getStudent();
        adapter = new StudentListAdapter(getContext(),data);
        listView.setAdapter(adapter);
        return view;
    }
    public void getStudent(){
        DatabaseReference ref = reference.getRef();
        ref.child("Students Lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                StudentProfileClass student = dataSnapshot.getValue(StudentProfileClass.class);
                data.add(student);
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

    // getting account will use in next fragment
//    public void getStudentsList(){
////        reference.child("Students_Lists");
//        DatabaseReference studentsRef = reference.getRef().child("Accounts");
////        studentsRef.child("Students Lists");
//
//        studentsRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                SignUpClass students = dataSnapshot.getValue(SignUpClass.class);
//                Log.d("Added",dataSnapshot.toString());
//                data.add(students);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                Log.d("Removed",dataSnapshot.toString());
//                SignUpClass students = dataSnapshot.getValue(SignUpClass.class);
//
//
//                if (data.contains(students)){
//                    int index = data.indexOf(students);
//                    data.remove(index);
//                    adapter.notifyDataSetChanged();
//
////                    int indexOfItem = data.indexOf(students);
////                    data.remove(indexOfItem);
////                    adapter.notifyDataSetChanged();
//                }
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void castingVariables(){
        listView = view.findViewById(R.id.student_list);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
//       DatabaseReference studentsRef = reference.getRef();
//        studentsRef.child("Students Lists");


    }

}
