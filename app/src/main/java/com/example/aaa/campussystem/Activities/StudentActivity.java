package com.example.aaa.campussystem.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.aaa.campussystem.Fragment.CompanyListsFragment;
import com.example.aaa.campussystem.Fragment.Jobsfragment;
import com.example.aaa.campussystem.Fragment.StudentListCompanyfragment;
import com.example.aaa.campussystem.LogInActivtty;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;

public class StudentActivity extends AppCompatActivity {
ImageButton view_jobs,company_list;
ImageButton back;
Spinner spinner;
ArrayAdapter<String> adapter;
String []items ={"update_profile","logout",""};
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        viewCasting();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        spinner.setAdapter(adapter);

        back.setVisibility(View.GONE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                 if (selectedItem.equals("update_profile")){
                    startActivity(new Intent(StudentActivity.this,StudentProfileActivity.class));
                }
                else if (selectedItem.equals("logout")){
                    auth.signOut();
                    startActivity(new Intent(StudentActivity.this, LogInActivtty.class));
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Jobsfragment());
            }
        });
        company_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new CompanyListsFragment());
            }
        });

    }
    public void changeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.job_frame,fragment);
        transaction.commit();
    }
    public void viewCasting(){

        view_jobs = findViewById(R.id.view_job);
        company_list = findViewById(R.id.view_company);
        back = findViewById(R.id.back_button1);
        spinner = findViewById(R.id.spinner);
        auth = FirebaseAuth.getInstance();
    }
}
