package com.example.aaa.campussystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaa.campussystem.Activities.AdminActivity;
import com.example.aaa.campussystem.Activities.CompanyActivity;
import com.example.aaa.campussystem.Activities.StudentActivity;
import com.example.aaa.campussystem.ModelClass.SignUpClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LogInActivtty extends AppCompatActivity {
    EditText email,password;
    Button logIn;
    TextView signUp;
    FirebaseAuth auth;
    ProgressDialog pd;
    FirebaseAuth.AuthStateListener listener;
    DatabaseReference reference;
    DatabaseReference accontRef;
    FirebaseDatabase database;
    ArrayList<SignUpClass> usersData;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activtty);
        viewCasting();

        pd = new ProgressDialog(this);
        pd.setTitle("Pleaes wait");
        pd.setMessage("Logging in......");

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Accounts");
        accontRef = reference.getRef();


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equalsIgnoreCase("Admin@mail.com")) {
                    if (password.getText().toString().equalsIgnoreCase("Admin")) {
                        startActivity(new Intent(LogInActivtty.this, AdminActivity.class));
                        finish();
                        Toast.makeText(LogInActivtty.this, "Hello Admin", Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    if (email.getText().toString().isEmpty()){
                        if (password.getText().toString().isEmpty()){
                            password.setError("");
                        }
                        email.setError("");
                    }
                    else {
                        accountLoginIN(email.getText().toString(), password.getText().toString());
                    }
                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivtty.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
//                    startActivity(new Intent(LogInActivtty.this,StudentActivty.class));
//                    finish();
                    Toast.makeText(LogInActivtty.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    public void accountLoginIN(String email,String password){
        pd.show();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                    getUsers();
//                    pd.dismiss();
                } else {
                    pd.dismiss();
                }
            }
        });

    }
    public void viewCasting(){
        email = findViewById(R.id.log_in_email);
        password = findViewById(R.id.log_in_password);
        logIn = findViewById(R.id.log_in);
        signUp = findViewById(R.id.move_to_signin);
    }


    public void getUsers(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        final String user_id = auth.getCurrentUser().getUid().toString();
        DatabaseReference ref = database.getReference().child("Accounts").child(user_id);
        accontRef.child(user_id);

//        DatabaseReference ref1 = database.getReference().child("Accounts").child(user_id).child("checkAccount");
//        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                key = dataSnapshot.getValue().toString();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //   String user_type = dataSnapshot.child("purpose").getValue().toString();
                String user_type = dataSnapshot.child("purpose").getValue().toString();
                String key = dataSnapshot.child("checkAccount").getValue().toString();
                if (user_type.equals("Company")){
                    if (key.equals("true")){
                        startActivity(new Intent(LogInActivtty.this, CompanyActivity.class));
                         finish();
                            pd.dismiss();
                            Toast.makeText(LogInActivtty.this, "Succesfull", Toast.LENGTH_SHORT).show();
                         }
                         pd.dismiss();
                    Toast.makeText(LogInActivtty.this, "Accounts disabled ", Toast.LENGTH_SHORT).show();
                }

                else if (user_type.equals("Student")){
                    if (key.equals("true")) {
                        startActivity(new Intent(LogInActivtty.this, StudentActivity.class));
                        finish();
                        pd.dismiss();
                        Toast.makeText(LogInActivtty.this, "Succesfull", Toast.LENGTH_SHORT).show();
                    }
                    pd.dismiss();
                    Toast.makeText(LogInActivtty.this, "Accounts disabled ", Toast.LENGTH_SHORT).show();
                }
                else if(user_type.equals("")){
                    pd.dismiss();
                    Toast.makeText(LogInActivtty.this, "Sorry no account found", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




//    public void getUsers(){
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        final String user_id = auth.getCurrentUser().getUid().toString();
//        DatabaseReference ref = database.getReference().child("Accounts").child(user_id).child("purpose");
//        accontRef.child(user_id);
//
//        DatabaseReference ref1 = database.getReference().child("Accounts").child(user_id).child("checkAccount");
//        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//               key = dataSnapshot.getValue().toString();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //   String user_type = dataSnapshot.child("purpose").getValue().toString();
//                String user_type = dataSnapshot.getValue().toString();
//                if (user_type.equals("Company")){
////                    if (key.equals("true")){
////                        pd.dismiss();
////                        startActivity(new Intent(LogInActivtty.this, CompanyActivity.class));
////                        finish();
////                        Toast.makeText(LogInActivtty.this, "Succesfull", Toast.LENGTH_SHORT).show();
////                    }
////                     pd.dismiss();
//                    startActivity(new Intent(LogInActivtty.this, CompanyActivity.class));
//                    finish();
//                    Toast.makeText(LogInActivtty.this, "Succesfull", Toast.LENGTH_SHORT).show();
//                }
//
//                else if (user_type.equals("Student")){
////                    if (key.equals("true")){
////                        pd.dismiss();
////                        startActivity(new Intent(LogInActivtty.this,StudentActivity.class));
////                        finish();
////                        Toast.makeText(LogInActivtty.this, "Succesfull", Toast.LENGTH_SHORT).show();
////                    }
//                    startActivity(new Intent(LogInActivtty.this,StudentActivity.class));
//                    finish();
//                    pd.dismiss();
//                    Toast.makeText(LogInActivtty.this, "Succesfull", Toast.LENGTH_SHORT).show();
//
//                }
//                else if(user_type.equals("")){
//                    pd.dismiss();
//                    Toast.makeText(LogInActivtty.this, "Sorry no account found", Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                }
//            } @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
//    public void getData(){
//        DatabaseReference ref1 = reference.getRef();
//        ref1.child("Accounts").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        listener.onAuthStateChanged(auth);
//    }
}

