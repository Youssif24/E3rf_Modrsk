package com.saad.youssif.e3rfmodrsk.Activities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.e3rfmodrsk.Fragments.StudentSignupFragment;
import com.saad.youssif.e3rfmodrsk.Fragments.TeacherSignupFragment;
import com.saad.youssif.e3rfmodrsk.Model.Student;
import com.saad.youssif.e3rfmodrsk.Model.Teacher;
import com.saad.youssif.e3rfmodrsk.Presenter.LoginPresenter;
import com.saad.youssif.e3rfmodrsk.Presenter.RegisterPresenter;
import com.saad.youssif.e3rfmodrsk.R;
import com.saad.youssif.e3rfmodrsk.SharedData.SharedPrefManager;
import com.saad.youssif.e3rfmodrsk.View.LoginView;
import com.saad.youssif.e3rfmodrsk.View.RegisterView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements LoginView,RegisterView {

    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    private Button student_btn;
    private Button teacher_btn;
    private LinearLayout main_signup;
    private LinearLayout signup_as;
    private LinearLayout studentLayout,teacherLayout;
    TextInputEditText stdNameEt,std_schoolEt,studYearEt,std_password;
    TextInputEditText tchNameEt,tch_addressEt,tch_specificEt,tch_phoneEt,tch_password,loginNameEt,loginPasswordEt;
    Spinner specificSpinner;
    static boolean check=false;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    ProgressDialog progressDialog;
    CircleImageView profileImg;
    private static final int SELECTED_PICTURE=1,CROP_PICTURE=2;
    Uri imageUri;
    String strImageProfileName,strImageProfilePath;
    static String type="";
    LoginPresenter loginPresenter;
    RegisterPresenter registerPresenter;
    ConnectivityManager connectivityManager;
    SharedPrefManager sharedPrefManager;




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gatherControls();
        setSpinnerArray();
        buttonsListeners();
        register_from_shrd();


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateData()&&checkConnection())
                {
                    if(getSelectedRdButton().equals("طالب"))
                    {
                        progressDialog.setMessage("جاري تسجيل الدخول.......");
                        progressDialog.show();
                        loginPresenter.studentLogin(loginNameEt.getText().toString(),loginPasswordEt.getText().toString());

                    }
                    else if(getSelectedRdButton().equals("معلم"))
                    {
                        progressDialog.setMessage("جاري تسجيل الدخول.......");
                        progressDialog.show();
                        loginPresenter.teacherLogin(loginNameEt.getText().toString(),loginPasswordEt.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,getSelectedRdButton(),Toast.LENGTH_LONG).show();
                    }
                }

                if (!checkConnection())
                {
                    Toast.makeText(LoginActivity.this,"عفوا لا يوجد اتصال بالانترنت",Toast.LENGTH_LONG).show();
                }



            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                btnSignup.startAnimation(clockwise);
                if(type.equals("طالب")&&validateStudentData())
                {
                    progressDialog.setMessage("جاري تسجيل حساب....");
                    progressDialog.show();
                    registerPresenter.studentRegister(stdNameEt.getText().toString(),std_password.getText().toString(),
                            std_schoolEt.getText().toString(),studYearEt.getText().toString(),strImageProfileName,strImageProfilePath
                    );

                }
                else if(type.equals("معلم")&&validateTeacherData())
                {
                    progressDialog.setMessage("جاري تسجيل حساب....");
                    progressDialog.show();
                    registerPresenter.teacherRegister(tchNameEt.getText().toString(),tch_password.getText().toString()
                            ,tch_phoneEt.getText().toString(),tch_addressEt.getText().toString()
                            ,"specif",strImageProfileName,strImageProfilePath
                            );
                }

            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(photoPickerIntent, getString(R.string.selectImage)), SELECTED_PICTURE);

            }
        });



    }

    public void save_user_data(String username,String id,String password)
    {
        if(getSelectedRdButton().equals("طالب"))
        {
            sharedPrefManager.setStd_name(username.trim());
        }
        else
        {
            sharedPrefManager.setTch_name(username.trim());
        }
        sharedPrefManager.setId(id.trim());
        sharedPrefManager.setPassword(password.trim());
        sharedPrefManager.setType(getSelectedRdButton().trim());
    }

    public void register_from_shrd()
    {

        if(sharedPrefManager.getType()!=null)
        {
            if(sharedPrefManager.getType().equals("معلم"))
            {
                String name=sharedPrefManager.getTch_name();
                String pass=sharedPrefManager.getPassword();
                if(!(TextUtils.isEmpty(name))&&!(TextUtils.isEmpty(pass)))
                {
                    startActivity(new Intent(LoginActivity.this,TeacherProfile.class));
                    LoginActivity.this.finish();

                }

            }
            else
            {
                String name=sharedPrefManager.getStd_name();
                String pass=sharedPrefManager.getPassword();
                if(!(TextUtils.isEmpty(name))&&!(TextUtils.isEmpty(pass)))
                {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                LoginActivity.this.finish();
                }
            }
        }


    }


    public String[] getSpecification()
    {
        final String[] specific = new String[1];

        final String specificArray[]=getResources().getStringArray(R.array.specificationArray);
        specificSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                specific[0] = specificArray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return specific;
    }


    public void buttonsListeners()
    {
        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSigninForm();
            }
        });

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignupForm();
                main_signup.setVisibility(View.GONE);
                signup_as.setVisibility(View.VISIBLE);
            }
        });
        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="طالب";
                /*Fragment fragment=new StudentSignupFragment();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frag_container,fragment);
                fragmentTransaction.commit();*/
               signup_as.setVisibility(View.GONE);
                main_signup.setVisibility(View.VISIBLE);
                teacherLayout.setVisibility(View.GONE);
                studentLayout.setVisibility(View.VISIBLE);
            }
        });

        teacher_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="معلم";
                /*Fragment fragment=new TeacherSignupFragment();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frag_container,fragment);
                fragmentTransaction.commit();*/
               signup_as.setVisibility(View.GONE);
                main_signup.setVisibility(View.VISIBLE);
                studentLayout.setVisibility(View.GONE);
                teacherLayout.setVisibility(View.VISIBLE);
            }
        });
    }


    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }

    public void gatherControls()
    {

        //Login Views
        loginNameEt=findViewById(R.id.login_nameEt);
        loginPasswordEt=findViewById(R.id.login_passwordEt);
        radioGroup=findViewById(R.id.typeRadioGroup);


        // Invokers and Animations
        tvSigninInvoker=(TextView) findViewById(R.id.tvSigninInvoker);
        tvSignupInvoker=(TextView) findViewById(R.id.tvSignupInvoker);

        btnSignin=(Button) findViewById(R.id.btnSignin);
        btnSignup=(Button) findViewById(R.id.btnSignup);
        student_btn=findViewById(R.id.student_btn);
        teacher_btn=findViewById(R.id.teacher_btn);

        llSignin=(LinearLayout) findViewById(R.id.llSignin);
        llSignup=(LinearLayout) findViewById(R.id.llSignup);
        main_signup=findViewById(R.id.main_signUp_layout);
        signup_as=findViewById(R.id.sign_as_layout);


        //Register Views
        profileImg=findViewById(R.id.reg_profileImg);

        //Student layout
        stdNameEt=findViewById(R.id.std_signUp_nameEt);
        studYearEt=findViewById(R.id.std_signUp_studYearEt);
        std_schoolEt=findViewById(R.id.std_signUp_schoolEt);
        std_password=findViewById(R.id.std_signUp_passwordEt);


        //Teacher Layout
        tchNameEt=findViewById(R.id.tch_signUp_nameEt);
        tch_phoneEt=findViewById(R.id.tch_signUp_phoneEt);
        tch_addressEt=findViewById(R.id.tch_signUp_addressEt);
        specificSpinner=findViewById(R.id.signUp_specificSpinner);
        tch_password=findViewById(R.id.tch_signUp_passwordEt);
        studentLayout=findViewById(R.id.studentLayout);
        teacherLayout=findViewById(R.id.teacher_layout);

        //objects
        progressDialog=new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loginPresenter=new LoginPresenter(this,this);
        registerPresenter=new RegisterPresenter(this,this);
        connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        sharedPrefManager=SharedPrefManager.getInstance(this);



    }

    public void setSpinnerArray()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.specificationArray,R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        specificSpinner.setAdapter(adapter);
    }

    @Override
    public void showStudentList(List<Student> loginResults) {
        if(loginResults.size()==0)
        {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this,"Please try again....",Toast.LENGTH_LONG).show();

        }
        else
        {
            save_user_data(loginResults.get(0).getStdName(),loginResults.get(0).getStdId(),loginPasswordEt.getText().toString().trim());
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this,loginResults.get(0).getStdId(),Toast.LENGTH_LONG).show();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();

        }
    }

    @Override
    public void showTeacherList(List<Teacher> loginResults) {
        if(loginResults.size()==0)
        {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this,"Please try again....",Toast.LENGTH_LONG).show();

        }
        else
        {
            save_user_data(loginResults.get(0).getName(),loginResults.get(0).getId(),loginPasswordEt.getText().toString().trim());
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this,loginResults.get(0).getName(),Toast.LENGTH_LONG).show();
            Intent teacherIntent=new Intent(LoginActivity.this,TeacherProfile.class);
            startActivity(teacherIntent);
            LoginActivity.this.finish();

        }
    }

    public String getSelectedRdButton()
    {
        int selectedId=radioGroup.getCheckedRadioButtonId();
        if(selectedId!=-1)
        {
            radioButton=findViewById(selectedId);
            return radioButton.getText().toString();
        }

        else
        {
            return "من فضلك حدد نوع الحساب";
        }

    }
    public boolean checkConnection()
    {
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();
    }
    public boolean validateData()
    {
        if(loginNameEt.getText().toString().trim().equals(""))
        {
            loginNameEt.setError("أدخل الإسم");
            return false;
        }
        else if(loginPasswordEt.getText().toString().trim().equals(""))
        {
            loginPasswordEt.setError("أدخل كلمة المرور");
            return false;
        }
        else
        {
            return true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECTED_PICTURE && resultCode== RESULT_OK) {
            try {
                imageUri = data.getData();

                Intent photoPickerCrop =new Intent("com.android.camera.action.CROP");
                photoPickerCrop.setDataAndType(imageUri,"image/*");
                photoPickerCrop.putExtra("crop", "true");
                // indicate aspect of desired crop
                photoPickerCrop.putExtra("aspectX", 1);
                photoPickerCrop.putExtra("aspectY", 1);
                // indicate output X and Y
                photoPickerCrop.putExtra("outputX", 360);
                photoPickerCrop.putExtra("outputY", 360);
                // retrieve data on return
                photoPickerCrop.putExtra("scaleUpIfNeeded", true);
                photoPickerCrop.putExtra("return-data", true);

                startActivityForResult(photoPickerCrop, CROP_PICTURE);
            }catch (ActivityNotFoundException ex){

            }
        }

        else if (requestCode == CROP_PICTURE) {

            if(data !=null) {
                Bundle bundle = data.getExtras();
                Bitmap selectedImage = bundle.getParcelable("data");
                profileImg.setImageBitmap(selectedImage);
                ByteArrayOutputStream byteArrayOutputStreamObject;
                byteArrayOutputStreamObject = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
                byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
                strImageProfileName = "E3rf_IMG_" + String.valueOf(System.currentTimeMillis());
                strImageProfilePath = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
            }
            else {
                Toast.makeText(LoginActivity.this, getString(R.string.cropImage),Toast.LENGTH_LONG).show();
            }

        }
    }

    public boolean validateStudentData()
    {
        if(stdNameEt.getText().toString().trim().equals(""))
        {
            stdNameEt.setError("أدخل الإسم");
            return false;
        }
        else if(studYearEt.getText().toString().trim().equals(""))
        {
            studYearEt.setError("أدخل المرحلة الدراسية");
            return false;
        }
        else if(std_schoolEt.getText().toString().trim().equals(""))
        {
            std_schoolEt.setError("أدخل المدرسة");
            return false;
        }
        else if(std_password.getText().toString().trim().equals(""))
        {
            std_password.setError("أدخل كلمة المرور");
            return false;
        }
        else
        {
            return true;
        }

    }
    public boolean validateTeacherData() {
        if (tchNameEt.getText().toString().trim().equals("")) {
            tchNameEt.setError("أدخل الإسم");
            return false;
        }
        else if (tch_addressEt.getText().toString().trim().equals("")) {
            tch_addressEt.setError("أدخل العنوان");
            return false;
        }
        else if (tch_phoneEt.getText().toString().trim().equals("")) {
            tch_phoneEt.setError("أدخل رقم الهاتف");
            return false;
        }
        else if (tch_password.getText().toString().trim().equals("")) {
            tch_password.setError("أدخل كلمة المرور");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void RegisterResponse(String response) {
        Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
        profileImg.setImageResource(R.drawable.ic_user);
        if(type.equals("طالب"))
        {
            stdNameEt.setText("");
            studYearEt.setText("");
            std_schoolEt.setText("");
            std_password.setText("");
        }
        else
        {
            tchNameEt.setText("");
            tch_addressEt.setText("");
            tch_phoneEt.setText("");
            tch_password.setText("");
        }
    }
}
