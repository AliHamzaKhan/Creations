package com.example.aaa.campussystem.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaa.campussystem.ModelClass.SignUpClass;
import com.example.aaa.campussystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by AAA on 3/18/2018.
 */

public class AccountsAdapter extends ArrayAdapter {
    ArrayList<SignUpClass> signUpClasses;
    TextView name,email,pass,purpose;
    Button delete,disable;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    String data;
    public AccountsAdapter(@NonNull Context context,ArrayList<SignUpClass> signUpClasses ) {
        super(context, 0,signUpClasses);
        this.signUpClasses = signUpClasses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.accounts_adapter,parent,false);
        }
        name = convertView.findViewById(R.id.name4);
        email = convertView.findViewById(R.id.email4);
        pass = convertView.findViewById(R.id.pass4);
        purpose = convertView.findViewById(R.id.purpose4);
        delete = convertView.findViewById(R.id.delete_button1);
        disable = convertView.findViewById(R.id.disable_button1);

        final SignUpClass signUpClass = signUpClasses.get(position);

        name.setText(signUpClass.getName());
        email.setText(signUpClass.getEmail());
        pass.setText(signUpClass.getPassword());
        purpose.setText(signUpClass.getPurpose());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DatabaseReference ref1 = reference.getRef();
                ref1.child("Accounts").child(signUpClass.getId()).setValue(null);
                ref1.child("Students Lists").child(signUpClass.getId()).setValue(null);
                ref1.child("Company List").child(signUpClass.getId()).setValue(null);
                ref1.child("Jobs_Lists").child(signUpClass.getId()).setValue(null);
                ref1.child("Applied_Students").child(signUpClass.getId()).setValue(null);
//                deleteUser(signUpClass.getEmail(),signUpClass.getPassword());
                getUSer(signUpClass.getEmail(),signUpClass.getPassword());
                Toast.makeText(getContext(), "Account deleted", Toast.LENGTH_SHORT).show();

            }
        });
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref2 = reference.getRef();
                if (checkAccount(signUpClass.getCheckAccount()).equals("true")){
                ref2.child("Accounts").child(signUpClass.getId()).child("checkAccount").setValue("false");
                Toast.makeText(getContext(), "disable account", Toast.LENGTH_SHORT).show();
                disable.setText("Enable");
                }
                else {
                    ref2.child("Accounts").child(signUpClass.getId()).child("checkAccount").setValue("true");
                    Toast.makeText(getContext(), "disable account", Toast.LENGTH_SHORT).show();
                    disable.setText("Disabled");
                }
            }
        });

        return convertView;
    }
//    public void signIn(final String email, final String password){
//        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                deleteUser(email,password);
//
//            }
//        });
//    }
//    public void deleteUser(String email,String password){
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        AuthCredential credential = EmailAuthProvider
//                .getCredential(email, password);
//
//        user.reauthenticate(credential)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        user.delete()
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Log.d(TAG, "User account deleted.");
//                                        }
//                                    }
//                                });
//                    }
//                });}
    private void getUSer(final String email1, final String password1){
        auth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                authDelete(email1,password1);
            }
        });
    }
private void authDelete(String email1,String password1) {
    final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    AuthCredential credential = EmailAuthProvider.getCredential(email1, password1);
    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            user.delete();


        }
    });
}
        private String checkAccount(String id){
             DatabaseReference ref = reference.getRef();
              ref.child("Accounts").child(id).child("checkAccount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 data = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return data;
        }
}
