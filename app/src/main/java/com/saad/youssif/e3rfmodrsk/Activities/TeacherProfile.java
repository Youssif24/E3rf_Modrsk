package com.saad.youssif.e3rfmodrsk.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Adapter.CommentsAdapter;
import com.saad.youssif.e3rfmodrsk.Adapter.ViewPagerAdapter;
import com.saad.youssif.e3rfmodrsk.Fragments.CommentsFragment;
import com.saad.youssif.e3rfmodrsk.Fragments.DetailsFragment;
import com.saad.youssif.e3rfmodrsk.Model.Teacher;
import com.saad.youssif.e3rfmodrsk.Presenter.TeacherProfilePresenter;
import com.saad.youssif.e3rfmodrsk.R;
import com.saad.youssif.e3rfmodrsk.SharedData.SharedPrefManager;
import com.saad.youssif.e3rfmodrsk.View.CommentsFragInterface;
import com.saad.youssif.e3rfmodrsk.View.TeacherProfileView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TeacherProfile extends AppCompatActivity implements TeacherProfileView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TeacherProfilePresenter teacherPresenter;
    SharedPrefManager sharedPrefManager;
    CircleImageView profile_img;
    TextView profile_name,profile_specification,profile_rate,cmnstNumTv;
    ProgressDialog progressDialog;
    ConnectivityManager connectivityManager;
    public static String phone,location;
    ImageButton callBtn,msgBtn;
    LinearLayout main_profile_layout;
    RelativeLayout noConnection_layout;
    Button tryAgainBtn;
    ImageView logout;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setCustomFont();

        logout=findViewById(R.id.logout);

        profile_img=findViewById(R.id.tch_profile_imgId);
        profile_name=findViewById(R.id.tch_profile_nameID);
        profile_specification=findViewById(R.id.tch_profile_specID);
        profile_rate=findViewById(R.id.tch_profile_rateID);
        cmnstNumTv=findViewById(R.id.commentNumTv);
        progressDialog=new ProgressDialog(this);
        connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        callBtn=findViewById(R.id.callBtn);
        msgBtn=findViewById(R.id.msgBtn);
        tryAgainBtn=findViewById(R.id.try_againBtn);
        main_profile_layout=findViewById(R.id.main_profile_layout);
        noConnection_layout=findViewById(R.id.no_internet_layout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.clearSharedData();
                Intent intent=new Intent(TeacherProfile.this,LoginActivity.class);
                startActivity(intent);
                TeacherProfile.this.finish();
            }
        });

        sharedPrefManager=SharedPrefManager.getInstance(this);
        teacherPresenter=new TeacherProfilePresenter(this,this);
        if(checkConnection())
        {
           runGettingData();
        }
        else
        {
            main_profile_layout.setVisibility(View.GONE);
            noConnection_layout.setVisibility(View.VISIBLE);
        }

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sharedPrefManager.getTch_phone()));
                if (ActivityCompat.checkSelfPermission(TeacherProfile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = sharedPrefManager.getTch_phone();  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });


        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("محاولة الإتصال .......");
                if(checkConnection())
                {
                    runGettingData();
                }
                else
                {
                    progressDialog.dismiss();
                }

            }
        });




    }

    public void runGettingData()
    {
        progressDialog.setMessage("تحميل البيانات......");
        progressDialog.show();
        teacherPresenter.getTchProfile(sharedPrefManager.getId().toString());
        main_profile_layout.setVisibility(View.VISIBLE);
        noConnection_layout.setVisibility(View.GONE);
    }

    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), "font/Cairo-Light.ttf"));
                }
            }
        }
    }

    public boolean checkConnection()
    {
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();
    }

    @Override
    public void showTeacherProfile(List<Teacher> teacherList) {

            if(teacherList.size()!=0)
            {
                Picasso.with(TeacherProfile.this).load(teacherList.get(0).getTchPhoto().toString()).into(profile_img);
                profile_name.setText(sharedPrefManager.getTch_name());
                profile_specification.setText(teacherList.get(0).getTchSpecification());
                profile_rate.setText(teacherList.get(0).getTchRate());
                Bundle bundle = new Bundle();
                bundle.putString("phone", teacherList.get(0).getTchPhone());
                bundle.putString("location",teacherList.get(0).getTchAddress());
                DetailsFragment fragobj = new DetailsFragment();
                fragobj.setArguments(bundle);
               // Toast.makeText(TeacherProfile.this,"phone is : "+teacherList.get(0).getTchPhone(),Toast.LENGTH_LONG).show();
                sharedPrefManager.setTch_phone(teacherList.get(0).getTchPhone().toString());
                sharedPrefManager.setTch_location(teacherList.get(0).getTchAddress().toString());
                //Toast.makeText(TeacherProfile.this,sharedPrefManager.getTch_location(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
            else
            {
                Toast.makeText(TeacherProfile.this,"لا يوجد تعليقات حاليا",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }


        }


}
