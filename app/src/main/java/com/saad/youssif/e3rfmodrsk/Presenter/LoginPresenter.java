package com.saad.youssif.e3rfmodrsk.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Model.StudentLogin;
import com.saad.youssif.e3rfmodrsk.Model.TeacherLogin;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiClient;
import com.saad.youssif.e3rfmodrsk.Retrofit.ApiInterface;
import com.saad.youssif.e3rfmodrsk.View.LoginView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    Context context;
    LoginView loginView;
    public LoginPresenter(Context context, LoginView loginView)
    {
        this.context=context;
        this.loginView=loginView;

    }

    public void studentLogin(String name, final String password)
    {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("std_login_username",name.trim());
        queryMap.put("std_login_password",password.trim());


        /* ApiClient.getClient() ===== returns retrofit object
         * which generates an implementation of the ApiInterface interface.
         * */
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<StudentLogin> call = apiInterface.stdLogin(queryMap);
        call.enqueue(new Callback<StudentLogin>() {
            @Override
            public void onResponse(Call<StudentLogin> call, Response<StudentLogin> response) {


                Log.e("LoginResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    loginView.showStudentList(response.body().getStudent());

                }
                else
                {
                    Toast.makeText(context,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<StudentLogin> call, Throwable t) {

            }

        });
    }

    public void teacherLogin(String name, final String password)
    {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("tch_login_username",name.trim());
        queryMap.put("tch_login_password",password.trim());


        /* ApiClient.getClient() ===== returns retrofit object
         * which generates an implementation of the ApiInterface interface.
         * */
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeacherLogin> call = apiInterface.tchLogin(queryMap);
        call.enqueue(new Callback<TeacherLogin>() {
            @Override
            public void onResponse(Call<TeacherLogin> call, Response<TeacherLogin> response) {


                Log.e("LoginResponse", response.raw().request().toString());

                if (response.isSuccessful()) {
                    loginView.showTeacherList(response.body().getTeacherResult());

                }
                else
                {
                    Toast.makeText(context,"error",Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<TeacherLogin> call, Throwable t) {

            }

        });
    }


}
