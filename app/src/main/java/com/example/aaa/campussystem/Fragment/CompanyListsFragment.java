package com.example.aaa.campussystem.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aaa.campussystem.Adapters.CompanyListAdapter;
import com.example.aaa.campussystem.ModelClass.CompanyProfile;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CompanyListsFragment extends Fragment {
View view;
ListView list;
FirebaseDatabase database;
DatabaseReference reference;
DatabaseReference compRef;
FirebaseAuth auth;
CompanyListAdapter adapter;
ArrayList<CompanyProfile> company;
    public CompanyListsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_company_lists, container, false);
       viewCasting();
        if (company == null){
            company = new ArrayList<>();
        }
        adapter = new CompanyListAdapter(getContext(),company);
        getCompany();
        list.setAdapter(adapter);

       return view;

    }
    public void getCompany(){
        compRef = reference.getRef();
        compRef.child("Company List").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyProfile profile = dataSnapshot.getValue(CompanyProfile.class);
                company.add(profile);
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

    public void viewCasting(){
        list = view.findViewById(R.id.company_list);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        auth = FirebaseAuth.getInstance();

    }
}
