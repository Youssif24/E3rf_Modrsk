package com.saad.youssif.e3rfmodrsk.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Model.TeacherLogin;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiClient;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiInterface;
import com.saad.youssif.e3rfmodrsk.View.RegisterView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    Context mContext;
    RegisterView registerView;

    public RegisterPresenter(Context context, RegisterView registerView) {
        this.mContext = context;
        this.registerView = registerView;
    }

    public void studentRegister(String stdName, String stdPassword, String stdSchool, String stdYear
            , String strImageProfileName, String strImageProfilePath) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.insertStudent(stdName,stdPassword,stdSchool,stdYear,strImageProfileName,strImageProfilePath);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                Log.e("StudentRegisterResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                   registerView.RegisterResponse(response.body());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

        });



    }

    public void teacherRegister(String tchName, String tchPassword, String phone, String address,String specification
            , String strImageProfileName, String strImageProfilePath) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.insertTeacher(tchName,tchPassword,phone,address,specification,strImageProfileName,strImageProfilePath);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                Log.e("TeacherRegisterResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    registerView.RegisterResponse(response.body());

                }
                else
                {
                    Toast.makeText(mContext,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

        });



    }

}
