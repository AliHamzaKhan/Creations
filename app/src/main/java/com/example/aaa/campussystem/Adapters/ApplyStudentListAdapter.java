package com.example.aaa.campussystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaa.campussystem.ModelClass.CompanyProfile;
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
 * Created by AAA on 3/15/2018.
 */

public class ApplyStudentListAdapter extends ArrayAdapter {
    ArrayList<StudentProfileClass> student;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;


    public ApplyStudentListAdapter(@NonNull Context context, ArrayList<StudentProfileClass> student) {
        super(context, 0,student);
        this.student = student;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      if (convertView == null){
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.apply_student_list_adapter,parent,false);
      }
      TextView name = convertView.findViewById(R.id.name7);
      TextView email = convertView.findViewById(R.id.email7);
      TextView phone = convertView.findViewById(R.id.phone7);
      TextView experience = convertView.findViewById(R.id.experience7);
      TextView qualification = convertView.findViewById(R.id.qualification7);
      Button accept = convertView.findViewById(R.id.accept);
      Button reject = convertView.findViewById(R.id.reject);

      database = FirebaseDatabase.getInstance();
      reference = database.getReference();
      auth = FirebaseAuth.getInstance();
        final StudentProfileClass students = student.get(position);
      accept.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String a = auth.getCurrentUser().getUid();
              CompanyProfile com = getComopany(a);
              Toast.makeText(getContext(), "Accepted", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(Intent.ACTION_SEND);
              intent.setType("text/plain");
              intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{students.getEmail()});
              intent.putExtra(Intent.EXTRA_SUBJECT, students.getName()+"\n"+students.getQualification()+"\n"+students.getExperience());
              try{
                  getContext().startActivity(Intent.createChooser(intent, "Send mail..."));
              }
              catch (android.content.ActivityNotFoundException ex){
                  Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
              }

          }
      });
      reject.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getContext(), "Rejected", Toast.LENGTH_SHORT).show();
              String a = auth.getCurrentUser().getUid();
              DatabaseReference ref1 = reference.getRef();
              ref1.child("Applied_Students").child(a).setValue(null);
              Toast.makeText(getContext(), "rejected", Toast.LENGTH_SHORT).show();
          }
      });


        name.setText(students.getName());
        email.setText(students.getEmail());
        phone.setText(students.getPhone());
        experience.setText(students.getExperience());
        qualification.setText(students.getQualification());

        return convertView;
    }
    private CompanyProfile getComopany(String a){
        DatabaseReference ref = reference.getRef();
        final CompanyProfile[] company = new CompanyProfile[1];

        ref.child("Company List").child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               company[0] = dataSnapshot.getValue(CompanyProfile.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return company[0];
    }
}
