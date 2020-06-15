package com.saad.youssif.e3rfmodrsk.Retrofit;



import com.saad.youssif.e3rfmodrsk.Model.CommentsResult;
import com.saad.youssif.e3rfmodrsk.Model.StudentLogin;
import com.saad.youssif.e3rfmodrsk.Model.TeacherLogin;
import com.saad.youssif.e3rfmodrsk.Model.TeacherProfileResult;
import com.saad.youssif.e3rfmodrsk.Model.TeachersListResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    //login
    @GET("std_login.php")
    Call<StudentLogin>stdLogin(@QueryMap Map<String , String > stdQueryMap);
    @GET("tch_login.php")
    Call<TeacherLogin>tchLogin(@QueryMap Map<String , String > tchQueryMap);

    //Register
    @POST("std_regist.php")
    @FormUrlEncoded
    Call<String> insertStudent(@Field("std_name") String name,
                            @Field("std_password") String password,
                            @Field("std_school") String school,
                            @Field("std_year") String stdYear,
                            @Field("userImageProfileName") String userImageProfileName,
                            @Field("userImageProfilePath") String userImageProfilePath
    );

    @POST("tch_regist.php")
    @FormUrlEncoded
    Call<String> insertTeacher(@Field("tch_name") String name,
                               @Field("tch_password") String password,
                               @Field("tch_phone") String phone,
                               @Field("tch_address") String address,
                               @Field("tch_specification") String specification,
                               @Field("userImageProfileName") String userImageProfileName,
                               @Field("userImageProfilePath") String userImageProfilePath
    );

    @POST("get_tch_profile.php")
    @FormUrlEncoded
    Call<TeacherProfileResult>tchProfile(@Field("tch_id")String tch_id);

    @POST("get_tch_comments.php")
    @FormUrlEncoded
    Call<CommentsResult>getComments(@Field("tch_id")String tch_id);

    @GET("get_teachers_list.php")
    Call<TeachersListResult>getTeachers();




}