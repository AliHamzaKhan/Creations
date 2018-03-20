package com.example.aaa.campussystem.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaa.campussystem.ModelClass.AppliesStudents;
import com.example.aaa.campussystem.ModelClass.CompanyPostAdd;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanyPostAJobActivity extends AppCompatActivity {
EditText designation1,sellery1,experience1,information1,email1,phoneNo1;
Button post_job;
FirebaseAuth auth;
FirebaseDatabase database;
DatabaseReference reference;
String upDateId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_post_ajob);

        viewCAsting();

        post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postJob();
            }
        });

    }

    private void postJob(){
        String a = auth.getCurrentUser().getUid().toString();

        String designation = designation1.getText().toString();
        String sellery = sellery1.getText().toString();
        String experience = experience1.getText().toString();
        String information = information1.getText().toString();
        String email = email1.getText().toString();
        String phoneNo = phoneNo1.getText().toString();


        DatabaseReference ref1 = reference.getRef();
        String id = upDateId;
        if (upDateId == null){
             id = ref1.push().getKey();
        }
        CompanyPostAdd add = new CompanyPostAdd(a,designation,sellery,experience,information,email,phoneNo,id,"true",new StudentProfileClass(null,null,null,null,null,null,null));

        ref1.child("Jobs_Lists").child(a).child(id).setValue(add);

        designation1.setText("");
        sellery1.setText("");
        experience1.setText("");
        information1.setText("");
        email1.setText("");
        phoneNo1.setText("");
        Toast.makeText(getApplicationContext(), "Job post successfully...", Toast.LENGTH_SHORT).show();
    }

    public void viewCAsting(){
        designation1 = findViewById(R.id.Designation);
        sellery1 = findViewById(R.id.sellery);
        experience1 = findViewById(R.id.experience);
        information1 = findViewById(R.id.information);
        email1 = findViewById(R.id.email);
        phoneNo1 = findViewById(R.id.phoneNo);
        post_job = findViewById(R.id.post_job);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();


    }
}
