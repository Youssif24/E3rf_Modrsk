package com.saad.youssif.e3rfmodrsk.Fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saad.youssif.e3rfmodrsk.Activities.TeacherProfile;
import com.saad.youssif.e3rfmodrsk.R;
import com.saad.youssif.e3rfmodrsk.SharedData.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    SharedPrefManager sharedPrefManager;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // Bundle bundle=getArguments();
        View view =inflater.inflate(R.layout.fragment_details, container, false);
        sharedPrefManager=SharedPrefManager.getInstance(getContext());


        TextView phoneTv=view.findViewById(R.id.frag_phoneTv);
        TextView addressTv=view.findViewById(R.id.frag_addressTv);

        phoneTv.setText(sharedPrefManager.getTch_phone());
        addressTv.setText(sharedPrefManager.getTch_location());


        return view;


    }


}
