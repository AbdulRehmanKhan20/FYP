package sc.example.com.comsats;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import sc.example.com.comsats.Model.CheckNetworkStatus;
import sc.example.com.comsats.Model.Users;

public class aAlumni extends URL implements View.OnClickListener {
    private String finalResult,Name,Email,Mobile,YearOfPassing,dateOfBirth,maritalStatus,profession,address,graduation;

    private EditText etName,etEmail,etMobile,etYearOfPassing,etDateOfBirth,etMaritalStatus,etProfession,etAddress,etGraduation;
    private Button addButton,logoutButton;

    private ProgressDialog progressDialog;


    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = BASE_URL+"add_Ralumni.php";


    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_alumni);
        getSupportActionBar().hide();

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etYearOfPassing = (EditText) findViewById(R.id.etYearOfPassing);
        etDateOfBirth = (EditText) findViewById(R.id.etDateOfBirth);
        etMaritalStatus = (EditText) findViewById(R.id.etMaritalStatus);
        etProfession = (EditText) findViewById(R.id.etProfession);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etGraduation = (EditText) findViewById(R.id.etGraduation);


        etYearOfPassing.setInputType(InputType.TYPE_NULL);
        etDateOfBirth.setInputType(InputType.TYPE_NULL);

        etYearOfPassing.setOnClickListener(this);
        etDateOfBirth.setOnClickListener(this);

        //new FetchHospitalDetailsAsyncTask().execute();

        addButton = (Button) findViewById(R.id.btnAdd);
        addButton.setOnClickListener(this);

        logoutButton = (Button) findViewById(R.id.btnLogout);

        logoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.etYearOfPassing:
                pickDate( etYearOfPassing);
                break;
            case R.id.etDateOfBirth:
                pickDate( etDateOfBirth);
                break;
            case R.id.btnLogout:
                try {
                    if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        finish();
                        Intent i = new Intent(aAlumni.this, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    } else {
                        Toast.makeText(aAlumni.this,
                                "Unable to connect to internet",
                                Toast.LENGTH_LONG).show();

                    }
                }catch (Exception e)
                {
                    e.getMessage();
                }

                break;
            case R.id.btnAdd:
                try {
                    if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        addAlumni();

                    } else {
                        Toast.makeText(aAlumni.this,
                                "Unable to connect to internet",
                                Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    e.getMessage();
                }

                break;

        }

    }


    void pickDate(final EditText et_Pick)
    {
        try{
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(aAlumni.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        et_Pick.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        picker.show();
    }catch (Exception e)
    {
        e.getMessage();
    }
    }



    private void addAlumni() {


        Name = etName.getText().toString();
        Email = etEmail.getText().toString();
        Mobile = etMobile.getText().toString();
        YearOfPassing = etYearOfPassing.getText().toString();
        dateOfBirth = etDateOfBirth.getText().toString();
        maritalStatus = etMaritalStatus.getText().toString();
        profession = etProfession.getText().toString();
        address = etAddress.getText().toString();
        graduation = etGraduation.getText().toString();

        if(!TextUtils.isEmpty(Name) || !TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Mobile) || !TextUtils.isEmpty(maritalStatus) || !TextUtils.isEmpty(profession) || !TextUtils.isEmpty(address) || !TextUtils.isEmpty(graduation) || !TextUtils.isEmpty(YearOfPassing) || !TextUtils.isEmpty(dateOfBirth))
        {
            try{
            insertAlumni(Name,Email,Mobile,maritalStatus,profession,address,graduation, YearOfPassing,dateOfBirth);
            }catch (Exception e)
            {
                e.getMessage();
            }
        }
        else
        {
            Toast.makeText(aAlumni.this, "Please Fill the Fields", Toast.LENGTH_LONG).show();

        }

    }

    public void insertAlumni(final String Name, final String Email , final String Mobile , final String maritalStatus , final String profession , final String address , final String graduation , final String YearOfPassing , final String dateOfBirth){

        class AlumniClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(aAlumni.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();

                try {
                    String message = httpResponseMsg;
                    Log.d("response",   "   .....  " + message );
                    Toast.makeText(aAlumni.this, message, Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(aAlumni.this,Dashboard.class));

                }catch (Exception e)
                {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("name",params[0]);

                hashMap.put("email",params[1]);

                hashMap.put("mobile",params[2]);

                hashMap.put("maritalStatus",params[3]);

                hashMap.put("profession",params[4]);

                hashMap.put("address",params[5]);

                hashMap.put("graduation",params[6]);

                hashMap.put("yearOfPassing",params[7]);

                hashMap.put("dateOfBirth",params[8]);

                hashMap.put("requestor_id",String.valueOf(Users.user_id));

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        try{
        AlumniClass alumni = new AlumniClass();


        alumni.execute(Name,Email,Mobile,YearOfPassing,dateOfBirth,maritalStatus,profession,address,graduation);
        }catch (Exception e)
        {
            e.getMessage();
        }

        }
}
