package com.saad.youssif.e3rfmodrsk.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saad.youssif.e3rfmodrsk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentSignupFragment extends Fragment {


    public StudentSignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_signup, container, false);
        return view;
    }

}
