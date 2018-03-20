package com.example.aaa.campussystem.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aaa.campussystem.Adapters.AccountsAdapter;
import com.example.aaa.campussystem.ModelClass.SignUpClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AccountsFragments extends Fragment {
View view;
ListView listView;
FirebaseDatabase database;
DatabaseReference reference;
DatabaseReference refAcounts;
ArrayList<SignUpClass> data;
AccountsAdapter adapter;
    public AccountsFragments() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accounts_fragments, container, false);
        if (data == null){
             data = new ArrayList<>();
        }
            viewCasting();
            getAccounts();
        adapter = new AccountsAdapter(getContext(),data);
        listView.setAdapter(adapter);
        return view;

    }


    public void viewCasting(){
        listView = view.findViewById(R.id.list_view3);
    }

    public void getAccounts(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        refAcounts = reference.getRef();

        refAcounts.child("Accounts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SignUpClass signUpClass = dataSnapshot.getValue(SignUpClass.class);
                data.add(signUpClass);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                SignUpClass signUpClass = dataSnapshot.getValue(SignUpClass.class);

//                /* for (SignUpClass sign : data){
//                    if (signUpClass.getId().equals(sign.getId())){
//                         data.remove(signUpClass);
//                         adapter.notifyDataSetChanged();
//                     }
//                  }*/

                int index = data.indexOf(signUpClass);
                if (index > 0){
                data.remove(index);
                adapter.notifyDataSetChanged();
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
}
