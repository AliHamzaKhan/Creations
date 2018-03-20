package com.example.aaa.campussystem.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.aaa.campussystem.ModelClass.StudentProfileClass;
import com.example.aaa.campussystem.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StudentProfileActivity extends AppCompatActivity {
FirebaseDatabase database;
DatabaseReference reference;
DatabaseReference cvRef;
FirebaseAuth auth;
EditText name,email,phone,qualification,experience;
ImageView image;
ImageButton back,update;
Button get_image,submit;
FirebaseStorage storage;
int PICK_IMAGE = 100;
 Uri uri;
 String myUri;
String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        viewCasting();
        initilizingFirebase();

        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()){
                    if (email.getText().toString().isEmpty()){
                        if (phone.getText().toString().isEmpty()){
                            if (experience.getText().toString().isEmpty()){
                                if (qualification.getText().toString().isEmpty()){
                                    qualification.setError("");
                                }
                                experience.setError("");
                            }
                            phone.setError("");
                        }
                        email.setError("");
                    }
                    name.setError("");
                }
                else{
                    String name1 = name.getText().toString();
                    String email1 = email.getText().toString();
                    String phone1 = phone.getText().toString();
                    String qualification1 = qualification.getText().toString();
                    String experience1 = experience.getText().toString();
                    upLoadImage(uri,a);
                    StudentProfileClass students = new StudentProfileClass(name1,email1,phone1,qualification1,experience1,myUri,a);
                    cvRef.child(a).setValue(students);
                }
                name.setText("");
                email.setText("");
                phone.setText("");
                qualification.setText("");
                experience.setText("");
                image.setImageResource(R.drawable.name);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDateProfile();
            }
        });
  }


    private void upDateProfile(){
        cvRef.child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentProfileClass student = dataSnapshot.getValue(StudentProfileClass.class);
                Log.d("TAG",dataSnapshot.toString());
                name.setText(student.getName());
                email.setText(student.getEmail());
                phone.setText(student.getPhone());
                qualification.setText(student.getQualification());
                experience.setText(student.getExperience());
                
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void upLoadImage(Uri uri, final String key){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        if (uri == null){
            return;
        }
        StorageReference storageReference = storage.getReference();
        StorageReference imageRef = storageReference.child(key+".jpg");

        UploadTask task = imageRef.putFile(uri);
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG","onFailure");
                pd.dismiss();

            }
        });
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downlaodImageUri = taskSnapshot.getDownloadUrl();
                String downloadStringUri = downlaodImageUri.toString();
                myUri = downloadStringUri;
                a = auth.getCurrentUser().getUid();
                cvRef.child(a).child("image").setValue(myUri);
                Log.d("TAG","onSuccess");
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Upload Successfull", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE){
            uri = data.getData();
        }
        try{
            image.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri));
        }catch (Exception e ){
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initilizingFirebase(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        cvRef = reference.getRef().child("Students Lists");
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        a = auth.getCurrentUser().getUid();
    }

    public void viewCasting(){
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone_no);
        qualification = findViewById(R.id.qualification);
        experience = findViewById(R.id.experience);
        get_image = findViewById(R.id.get_image);
        submit = findViewById(R.id.submit);
        image = findViewById(R.id.imageView);
        back = findViewById(R.id.back_button);
        update = findViewById(R.id.update_profile);
    }
}
