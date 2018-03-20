package com.example.aaa.campussystem.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragmentAdmin extends Fragment {
View view;
ListView list;
CompanyListAdapter adapter;
ArrayList<CompanyProfile> companyProfiles;
FirebaseDatabase database;
DatabaseReference reference;



    public CompanyFragmentAdmin() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_company_fragment_admin, container, false);

        list = view.findViewById(R.id.company_list);

        if (companyProfiles == null){
            companyProfiles = new ArrayList<>();
        }


        getCompany();

        adapter = new CompanyListAdapter(getContext(),companyProfiles);
        list.setAdapter(adapter);

        return view;
    }
private void getCompany(){
    database  = FirebaseDatabase.getInstance();
    reference = database.getReference();

        DatabaseReference companyRef = reference.getRef().child("Company List");
//        companyRef
        companyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              Log.d("ADDED",dataSnapshot.getValue().toString());
                CompanyProfile companyProfile = dataSnapshot.getValue(CompanyProfile.class);
                companyProfiles.add(companyProfile);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "helloo added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("Company Removes",dataSnapshot.getValue().toString());
//                CompanyProfile companyProfile = dataSnapshot.getValue(CompanyProfile.class);
//                int indexOf = companyProfiles.indexOf(companyProfile);
//                companyProfiles.remove(indexOf);
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
}
