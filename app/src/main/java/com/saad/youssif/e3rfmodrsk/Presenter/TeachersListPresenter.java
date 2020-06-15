package com.saad.youssif.e3rfmodrsk.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Model.TeacherProfileResult;
import com.saad.youssif.e3rfmodrsk.Model.TeachersListResult;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiClient;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiInterface;
import com.saad.youssif.e3rfmodrsk.View.TeachersView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersListPresenter {
    Context mContext;
    TeachersView teachersView;

    public TeachersListPresenter(Context mContext, TeachersView teachersView) {
        this.mContext = mContext;
        this.teachersView = teachersView;
    }


    public void getTeachers()
    {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeachersListResult> call=apiInterface.getTeachers();
        call.enqueue(new Callback<TeachersListResult>() {
            @Override
            public void onResponse(Call<TeachersListResult> call, Response<TeachersListResult> response) {


                Log.e("TeacherListResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    teachersView.getTeachers(response.body().getTchListResult());
                   // teacherProfileView.showTeacherProfile(response.body().getTchResult());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<TeachersListResult> call, Throwable t) {

            }

        });
    }
}
