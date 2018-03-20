package com.example.aaa.campussystem.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aaa.campussystem.Fragment.AppliedStudentsFragment;
import com.example.aaa.campussystem.Fragment.StudentListCompanyfragment;
import com.example.aaa.campussystem.LogInActivtty;
import com.example.aaa.campussystem.R;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_SHORT;

public class CompanyActivity extends AppCompatActivity {
ImageButton studentList,applies_list;
Spinner spinner;
ImageButton back;
String[] data = {"update profile","post a job","log out",""};
ArrayAdapter<String> adapter;
FirebaseAuth auth;
RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
viewCasting();
        studentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new StudentListCompanyfragment());
            }
        });
        applies_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new AppliedStudentsFragment());
            }
        });

        back.setVisibility(View.GONE);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selectedItem = parent.getItemAtPosition(position).toString();


               if(selectedItem.equals("update profile"))
               {
 // did changed startActivty(new Intent(CompanyActivity.this,CompanyProfileActivity.class)) because opening without selecting
                  Intent intent = new Intent(CompanyActivity.this,CompanyProfileActivity.class);
                  startActivity(intent);
               }
               else if (selectedItem.equals("post a job")){
                   startActivity(new Intent(CompanyActivity.this,CompanyPostAJobActivity.class));
                   Toast.makeText(CompanyActivity.this, "waiting for development", Toast.LENGTH_SHORT).show();
               }
               else if (selectedItem.equals("log out")){
                   auth.signOut();
                   startActivity(new Intent(CompanyActivity.this,LogInActivtty.class));
                   finish();
               }

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }

    public void changeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame1,fragment);
        transaction.commit();
    }

    public void viewCasting(){
        studentList = findViewById(R.id.button2);
        applies_list = findViewById(R.id.button3);
        spinner = findViewById(R.id.spinner);
        back = findViewById(R.id.back_button1);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,data);
        spinner.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();
        layout = findViewById(R.id.toolbar_layout);
    }
}
