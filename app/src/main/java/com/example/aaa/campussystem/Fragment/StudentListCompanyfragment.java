package com.example.aaa.campussystem.Fragment;


import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aaa.campussystem.Adapters.StudentListAdapter;
import com.example.aaa.campussystem.ModelClass.SignUpClass;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListCompanyfragment extends Fragment {
View view;
ListView listView;
FirebaseDatabase database;
DatabaseReference reference;
DatabaseReference stRef;
StudentListAdapter adapter;
ArrayList<StudentProfileClass> studentProfileClasses;
    public StudentListCompanyfragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_list_companyfragment, container, false);
        viewCasting();

        if (studentProfileClasses == null){
            studentProfileClasses = new ArrayList<>();
        }

        getData();
        adapter = new StudentListAdapter(getContext(),studentProfileClasses);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                stRef = reference.getRef();
                stRef.child("Students Lists").child(studentProfileClasses.get(position).getId()).setValue(null);

                return true;
            }
        });
        adapter.notifyDataSetChanged();
        return view;
    }

    private void getData(){

        DatabaseReference ref = reference.getRef();
        ref.child("Students Lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                StudentProfileClass students = dataSnapshot.getValue(StudentProfileClass.class);
                studentProfileClasses.add(students);
                adapter.notifyDataSetChanged();
                Log.d("Added",dataSnapshot.toString());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                StudentProfileClass students = dataSnapshot.getValue(StudentProfileClass.class);
                Log.d("Removed",dataSnapshot.toString());
//                int index = data.indexOf(students);
//                data.remove(index);
//                adapter.notifyDataSetChanged();
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
        listView = view.findViewById(R.id.student_list_company);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

}
