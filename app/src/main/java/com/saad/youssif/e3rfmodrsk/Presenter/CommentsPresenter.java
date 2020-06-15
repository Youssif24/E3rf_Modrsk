package com.saad.youssif.e3rfmodrsk.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Model.CommentsResult;
import com.saad.youssif.e3rfmodrsk.Model.TeacherProfileResult;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiClient;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiInterface;
import com.saad.youssif.e3rfmodrsk.View.CommentsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsPresenter {

    Context mContext;
    CommentsView commentsView;

    public CommentsPresenter (Context context,CommentsView commentsView)
    {
        this.commentsView=commentsView;
        this.mContext=context;

    }

    public void getComments(String tch_id)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CommentsResult> call=apiInterface.getComments(tch_id);
        call.enqueue(new Callback<CommentsResult>() {
            @Override
            public void onResponse(Call<CommentsResult> call, Response<CommentsResult> response) {


                Log.e("TeacherCommentsResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    commentsView.getTchComments(response.body().getTchResult());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<CommentsResult> call, Throwable t) {

            }

        });
    }
}
