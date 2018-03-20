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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaa.campussystem.ModelClass.AppliesStudents;
import com.example.aaa.campussystem.ModelClass.CompanyPostAdd;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by AAA on 3/17/2018.
 */

public class JobAdapter extends ArrayAdapter {
    TextView designation1, sellery, experience, information, email, phoneNo;

    ArrayList<CompanyPostAdd> jobs;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference ref1;
    FirebaseAuth auth;
    DatabaseReference ref2;
    String a;
    StudentProfileClass appliesStudents;
    StudentProfileClass studentProfileClass;
    public JobAdapter(@NonNull Context context, ArrayList<CompanyPostAdd> jobs,String a) {
        super(context, 0, jobs);
        this.jobs = jobs;
        this.a = a;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.job_adapter, parent, false);
        }
initilizingFirebase();
        designation1 = convertView.findViewById(R.id.Designation2);
        sellery = convertView.findViewById(R.id.sellery2);
        experience = convertView.findViewById(R.id.experience2);
        information = convertView.findViewById(R.id.information2);
        email = convertView.findViewById(R.id.email2);
        phoneNo = convertView.findViewById(R.id.phoneNo2);
        ImageButton apply = convertView.findViewById(R.id.apply_for_job);

        final CompanyPostAdd job = jobs.get(position);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = auth.getCurrentUser().getUid();
                ref1 = reference.getRef();
//                ref1.child("Jobs_Lists").child(job.getId()).child("applied_student").child(a).setValue(upDateProfile());
                ref1.child("Applied_Students").child(job.getCompanyId()).child(job.getId()).setValue(getStudentProfile());

            }
        });

        designation1.setText(job.getDesignation());
        sellery.setText(job.getSellery());
        experience.setText(job.getExperience());
        information.setText(job.getInformation());
        email.setText(job.getEmail());
        phoneNo.setText(job.getPhoneNo());

        return convertView;
    }
    public void initilizingFirebase() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
    }


    public StudentProfileClass getStudentProfile(){

     ref2 = reference.getRef().child("Students Lists").child(a);
    ref2.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            studentProfileClass = dataSnapshot.getValue(StudentProfileClass.class);
            Log.d("Student",dataSnapshot.getValue().toString());
            Toast.makeText(getContext(), "yeah you have applied", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    return studentProfileClass;
}
}
