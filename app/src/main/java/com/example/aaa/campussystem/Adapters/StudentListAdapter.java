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
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by AAA on 3/15/2018.
 */

public class StudentListAdapter extends ArrayAdapter {
    ArrayList<StudentProfileClass> SignUp;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    public StudentListAdapter(@NonNull Context context, ArrayList<StudentProfileClass> SignUp) {
        super(context,0, SignUp);
        this.SignUp = SignUp;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_list_adapter,parent,false);
        }

        TextView name  = convertView.findViewById(R.id.student_name);
        TextView email = convertView.findViewById(R.id.student_email);
        TextView qualification = convertView.findViewById(R.id.student_qualification);
        TextView expeeince = convertView.findViewById(R.id.student_experience);
        TextView phone = convertView.findViewById(R.id.student_phone);

        final StudentProfileClass signUpClass = SignUp.get(position);
        name.setText(signUpClass.getName());
        email.setText(signUpClass.getEmail());
        qualification.setText(signUpClass.getQualification());
        expeeince.setText(signUpClass.getExperience());
        phone.setText(signUpClass.getPhone());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();



//         Button delete = convertView.findViewById(R.id.delete_button);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseReference stRef = reference.getRef();
//                stRef.child("Accounts").child(signUpClass.getId()).setValue(null);
//                Toast.makeText(getContext(), "Account deleted", Toast.LENGTH_SHORT).show();
////                deleteUser(signUpClass.getEmail(),signUpClass.getPassword());
//
//            }
//        });
//
//        Button disable =  convertView.findViewById(R.id.disable_button);
//        disable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseReference stRef = reference.getRef();
//                stRef.child("Accounts").child(signUpClass.getId()).child("checkAccount").setValue(false);
//                Toast.makeText(getContext(), "disable account", Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }

}
