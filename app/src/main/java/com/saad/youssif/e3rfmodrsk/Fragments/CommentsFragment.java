package com.saad.youssif.e3rfmodrsk.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saad.youssif.e3rfmodrsk.Adapter.CommentsAdapter;
import com.saad.youssif.e3rfmodrsk.Model.Comment;
import com.saad.youssif.e3rfmodrsk.Presenter.CommentsPresenter;
import com.saad.youssif.e3rfmodrsk.R;
import com.saad.youssif.e3rfmodrsk.SharedData.SharedPrefManager;
import com.saad.youssif.e3rfmodrsk.View.CommentsView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment implements CommentsView {

    SharedPrefManager sharedPrefManager;
    RecyclerView recyclerView;
    public static int cmnts_num;
    TextView cmnts_number;
    LinearLayout commentLayout;

    public CommentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_comments, container, false);
        recyclerView=view.findViewById(R.id.commentsRecycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        sharedPrefManager=SharedPrefManager.getInstance(getContext());
        CommentsPresenter commentsPresenter=new CommentsPresenter(getContext(),this);
        commentsPresenter.getComments(sharedPrefManager.getId());
        if(sharedPrefManager.getType().equals("معلم"))
        {
            commentLayout=view.findViewById(R.id.commentLayout);
            commentLayout.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void getTchComments(List<Comment> commentsList) {
        CommentsAdapter commentsAdapter=new CommentsAdapter(commentsList,getContext());
        recyclerView.setAdapter(commentsAdapter);

    }
}
