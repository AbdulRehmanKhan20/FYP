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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import sc.example.com.comsats.Model.CheckNetworkStatus;
import sc.example.com.comsats.Model.Users;

public class aReserveAuditorium extends URL implements View.OnClickListener {
    private String finalResult,Reservation,startDate,endDate;

    private EditText et_Reserve,et_startDate,et_endDate;
    private Button addButton,logoutButton;

    private ProgressDialog progressDialog;


    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = BASE_URL+"add_ReserveAuditorium.php";


    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_reserve_auditorium);
        getSupportActionBar().hide();

        et_Reserve = (EditText) findViewById(R.id.etReserve);
        et_startDate = (EditText) findViewById(R.id.et_startDate);
        et_endDate = (EditText) findViewById(R.id.et_endDate);


        et_startDate.setInputType(InputType.TYPE_NULL);
        et_endDate.setInputType(InputType.TYPE_NULL);

        et_startDate.setOnClickListener(this);
        et_endDate.setOnClickListener(this);

        //new FetchHospitalDetailsAsyncTask().execute();

        addButton = (Button) findViewById(R.id.btnAdd);
        addButton.setOnClickListener(this);

        logoutButton = (Button) findViewById(R.id.btnLogout);

        logoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.et_startDate:
                pickDate( et_startDate);
                break;
            case R.id.et_endDate:
                pickDate( et_endDate);
                break;
            case R.id.btnLogout:
                try {
                    if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        finish();
                        Intent i = new Intent(aReserveAuditorium.this, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    } else {
                        Toast.makeText(aReserveAuditorium.this,
                                "Unable to connect to internet",
                                Toast.LENGTH_LONG).show();

                    }
                }catch (Exception e)
                {
                    e.getMessage();
                }

                break;
            case R.id.btnAdd:
                try{
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    addAuditorium();
                    finish();
                    startActivity(new Intent(aReserveAuditorium.this,aAuditorium.class));
                } else {
                    Toast.makeText(aReserveAuditorium.this,
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
        final int hour = cldr.get(Calendar.HOUR);
        final int minute = cldr.get(Calendar.MINUTE);
        final int second = cldr.get(Calendar.SECOND);
        final int milliSecond = cldr.get(Calendar.MILLISECOND);
        // date picker dialog
        picker = new DatePickerDialog(aReserveAuditorium.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        et_Pick.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth+ " "+hour+":"+minute+":"+second);
                    }
                }, year, month, day);
        picker.getDatePicker().setMinDate(System.currentTimeMillis());
        picker.show();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }



    private void addAuditorium() {
        try{
            Reservation = et_Reserve.getText().toString();
            startDate = et_startDate.getText().toString();
            endDate = et_endDate.getText().toString();

            if(!TextUtils.isEmpty(Reservation) || !TextUtils.isEmpty(startDate) || !TextUtils.isEmpty(endDate))
            {
                UserLoginFunction( Reservation, startDate, endDate);
            }
            else
            {
                Toast.makeText(aReserveAuditorium.this, "Please Fill the Fields", Toast.LENGTH_LONG).show();

            }
        }catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void UserLoginFunction(final String Reservation, final String startDate , final String endDate){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    progressDialog = ProgressDialog.show(aReserveAuditorium.this, "Loading Data", null, true, true);
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                try{
                progressDialog.dismiss();

                    String message = httpResponseMsg;
                    Log.d("response",   "   .....  " + message );

                        Toast.makeText(aReserveAuditorium.this, message, Toast.LENGTH_LONG).show();

                }catch (Exception e)
                {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("reason",params[0]);

                hashMap.put("startDate",params[1]);

                hashMap.put("endDate",params[2]);

                hashMap.put("requestor_id",String.valueOf(Users.user_id));

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();
        try{
        userLoginClass.execute(Reservation,startDate,endDate);
        }catch (Exception e)
        {
            e.getMessage();
        }
    }
}
