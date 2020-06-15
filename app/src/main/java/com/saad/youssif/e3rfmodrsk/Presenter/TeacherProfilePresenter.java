package com.saad.youssif.e3rfmodrsk.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Model.Teacher;
import com.saad.youssif.e3rfmodrsk.Model.TeacherProfileResult;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiClient;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiInterface;
import com.saad.youssif.e3rfmodrsk.View.TeacherProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherProfilePresenter {
    Context mContext;
    TeacherProfileView teacherProfileView;

    public  TeacherProfilePresenter(Context context,TeacherProfileView tch_view)
    {
        this.mContext=context;
        this.teacherProfileView=tch_view;

    }

    public void getTchProfile(String tch_id)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeacherProfileResult> call=apiInterface.tchProfile(tch_id);
        call.enqueue(new Callback<TeacherProfileResult>() {
            @Override
            public void onResponse(Call<TeacherProfileResult> call, Response<TeacherProfileResult> response) {


                Log.e("TeacherProfileResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    teacherProfileView.showTeacherProfile(response.body().getTchResult());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<TeacherProfileResult> call, Throwable t) {

            }

        });

    }
}
