package com.example.aaa.campussystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aaa.campussystem.Activities.AdminActivity;
import com.example.aaa.campussystem.Activities.CompanyActivity;
import com.example.aaa.campussystem.Activities.StudentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button admin = findViewById(R.id.admin);
//        Button company = findViewById(R.id.company);
//        Button student = findViewById(R.id.student);
//
//        admin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AdminActivity.class));
//            }
//        });
//        company.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CompanyActivity.class));
//            }
//        });
//        student.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, StudentActivity.class));
//            }
//        });
        Thread timer = new Thread(){

            public void run(){

                try{
                    sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(MainActivity.this,LogInActivtty.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
