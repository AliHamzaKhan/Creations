package com.example.aaa.campussystem.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aaa.campussystem.ModelClass.CompanyPostAdd;
import com.example.aaa.campussystem.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by AAA on 3/19/2018.
 */

public class JobAdapterListView extends ArrayAdapter {
    ArrayList<CompanyPostAdd> postAdds;
    public JobAdapterListView(@NonNull Context context, ArrayList<CompanyPostAdd> postAdds) {
        super(context, 0,postAdds);
        this.postAdds = postAdds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.job_adapter_list_view,parent,false);
        }
        TextView desination = convertView.findViewById(R.id.designantion3);
        TextView information = convertView.findViewById(R.id.information3);
        TextView sellery = convertView.findViewById(R.id.sellery3);
        TextView experience = convertView.findViewById(R.id.designantion3);

        CompanyPostAdd add = postAdds.get(position);
        desination.setText(add.getDesignation());
        information.setText(add.getInformation());
        sellery.setText(add.getSellery());
        experience.setText(add.getExperience());



        return convertView;
    }
}
