package com.example.aaa.campussystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aaa.campussystem.Activities.CompanyActivity;
import com.example.aaa.campussystem.Activities.StudentActivity;
import com.example.aaa.campussystem.ModelClass.SignUpClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText name,email,password;
    Button signUp;
    Spinner spinner;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference accountRef;
    FirebaseAuth auth;
    String userId;
    ProgressDialog pd;
    ArrayAdapter<String> adpater;
    String[] data = {"Student","Company"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        viewCasting();
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Accounts");
        accountRef = reference.getRef();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().isEmpty()){
                    if (password.getText().toString().isEmpty()){
                        if (name.getText().toString().isEmpty()){
                           name.setError("Name is necessary");
                        }
                        password.setError("why password is empty?");
                    }
                    email.setError("wheres email");
                }
                else {
                    createAccount
                            (email.getText().toString(), password.getText().toString(),
                             name.getText().toString(), data[spinner.getSelectedItemPosition()]);
                }
            }

        });

    }

    public void createAccount(final String email, final String password, final String name, final String purpose){
        pd.show();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    String userUuid = auth.getCurrentUser().getUid().toString();
                    SignUpClass accounts = new SignUpClass(name,email,password,purpose,userUuid,"true");
                    accountRef.child(userUuid).setValue(accounts);

                    if (purpose.equals("Student")){
                        pd.dismiss();
                        startActivity(new Intent(SignUpActivity.this, StudentActivity.class));
                        Toast.makeText(SignUpActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if (purpose.equals("Company")){
                        pd.dismiss();
                        Toast.makeText(SignUpActivity.this, "Suucessfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, CompanyActivity.class));
                        finish();
                    }


                }
                else{
                    pd.dismiss();
                    Toast.makeText(SignUpActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void viewCasting(){
        name = findViewById(R.id.sign_in_name);
        email = findViewById(R.id.sign_in_email);
        password = findViewById(R.id.sign_in_password);
        signUp = findViewById(R.id.sign_in_button);
        spinner = findViewById(R.id.spinner);
        pd = new ProgressDialog(this);
        pd.setTitle("Please Wait");
        pd.setMessage("Signing Up");

        adpater = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,data);
        spinner.setAdapter(adpater);
    }
}