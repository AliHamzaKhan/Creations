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

import com.example.aaa.campussystem.Fragment.AccountsFragments;
import com.example.aaa.campussystem.Fragment.CompanyFragmentAdmin;
import com.example.aaa.campussystem.Fragment.StudentFragmentAdmin;
import com.example.aaa.campussystem.LogInActivtty;
import com.example.aaa.campussystem.R;

public class AdminActivity extends AppCompatActivity {
Spinner spinner;
String dropItems[] = {"","View_Accounts","logout"};
ArrayAdapter<String> adapter;
ImageButton students,company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

viewCasting();

        changeFragment(new AccountsFragments());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("")){

                }
                else if (selectedItem.equals("View_Accounts")){
                        changeFragment(new AccountsFragments());
                }
                else if (selectedItem.equals("logout")){
                    startActivity(new Intent(AdminActivity.this, LogInActivtty.class));
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       students.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            changeFragment(new StudentFragmentAdmin());
           }
       });
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            changeFragment(new CompanyFragmentAdmin());
            }
        });
    }
    public void changeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
    public void viewCasting(){
        students = findViewById(R.id.students_fragment);
        company = findViewById(R.id.company_fragment);

        ImageButton back = findViewById(R.id.back_button1);
        back.setVisibility(View.GONE);
        spinner = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,dropItems);
        spinner.setAdapter(adapter);
    }
}
