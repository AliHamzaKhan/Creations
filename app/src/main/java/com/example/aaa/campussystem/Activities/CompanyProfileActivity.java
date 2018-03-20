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

import com.example.aaa.campussystem.ModelClass.CompanyPostAdd;
import com.example.aaa.campussystem.ModelClass.CompanyProfile;
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

public class CompanyProfileActivity extends AppCompatActivity {

EditText name1,address1,faculty1,phone1;
ImageButton back_button,update_button;
Button get_image,submit;
ImageView image;
String name,address,faculty,phone;
int PICK_IMAGE;
Uri uri;
FirebaseDatabase database;
FirebaseAuth auth;
DatabaseReference reference;
FirebaseStorage storage;
DatabaseReference profileRef;
String a;
String myUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        viewCasting();
        intializingFirebase();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDateData();
            }
        });
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
                if (name1.getText().toString().isEmpty()){
                    if (address1.getText().toString().isEmpty()){
                        if (faculty1.getText().toString().isEmpty()){
                            if (phone1.getText().toString().isEmpty()){
                                phone1.setError("");
                            }
                            faculty1.setError("");
                        }
                        address1.setError("");
                    }
                    name1.setError("");
                }
                else{
                    getTextData();
                    upLoadImage(uri,a);
                    CompanyProfile company = new CompanyProfile(name,address,faculty,phone,myUri,a);
   //                 new CompanyPostAdd(null,null,null,null,null,null,null,true));
                    profileRef.child(a).setValue(company);
                }

                name1.setText("");
                address1.setText("");
                faculty1.setText("");
                phone1.setText("");
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              onBackPressed();
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
                profileRef.child(a).child("image").setValue(myUri);
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

    private void upDateData(){
        profileRef.child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CompanyProfile student = dataSnapshot.getValue(CompanyProfile.class);
                Log.d("TAG",dataSnapshot.toString());
                name1.setText(student.getName());
                address1.setText(student.getAddress());
                faculty1.setText(student.getFaculty());
                phone1.setText(student.getPhone());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getTextData(){
        name = name1.getText().toString();
        address = address1.getText().toString();
        faculty = faculty1.getText().toString();
        phone = phone1.getText().toString();
    }


    public void viewCasting(){
        name1 = findViewById(R.id.name1);
        address1 = findViewById(R.id.address1);
        faculty1 = findViewById(R.id.faculty1);
        phone1 = findViewById(R.id.phone1);
        get_image = findViewById(R.id.company_get_image);
        submit = findViewById(R.id.submit_profile);
        image = findViewById(R.id.company_image);
        back_button = findViewById(R.id.back_button);
        update_button = findViewById(R.id.update_profile);
    }


    private void intializingFirebase(){
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = database.getReference();
        profileRef = reference.child("Company List");
        storage = FirebaseStorage.getInstance();
        a = auth.getCurrentUser().getUid();
    }
}
