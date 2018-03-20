package com.example.aaa.campussystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaa.campussystem.Activities.CompanyMoveToActivity;
import com.example.aaa.campussystem.ModelClass.CompanyProfile;
import com.example.aaa.campussystem.R;


import java.util.ArrayList;

/**
 * Created by AAA on 3/15/2018.
 */

public class CompanyListAdapter extends ArrayAdapter {
    ArrayList<CompanyProfile> companyProfiles;
    public CompanyListAdapter(@NonNull Context context, ArrayList<CompanyProfile> companyProfiles) {
        super(context, 0,companyProfiles);
        this.companyProfiles = companyProfiles;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if (convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_list_adapter,parent,false);
       }

        ImageView image = convertView.findViewById(R.id.company_list_image);
        TextView name = convertView.findViewById(R.id.company_lis_name);
        TextView faculty = convertView.findViewById(R.id.company_list_faculty);
        ImageButton move = convertView.findViewById(R.id.company_move_to_button);

        final CompanyProfile companyProfile =  companyProfiles.get(position);

        name.setText(companyProfile.getName());
        faculty.setText(companyProfile.getFaculty());
//        Glide.with(getContext()).load(companyProfile.getImage()).into(image);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ""+companyProfile.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CompanyMoveToActivity.class);
                intent.putExtra("name",companyProfile.getName());
                intent.putExtra("faculty",companyProfile.getFaculty());
                intent.putExtra("phone",companyProfile.getPhone());
                intent.putExtra("address",companyProfile.getAddress());
                intent.putExtra("image",companyProfile.getImage());
                intent.putExtra("auth",companyProfile.getId());
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }


}
