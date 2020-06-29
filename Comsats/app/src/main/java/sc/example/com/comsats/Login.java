package sc.example.com.comsats;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;

import sc.example.com.comsats.Model.Users;

public class Login extends URL  {


    EditText Email, Password;
    Button LogIn,register ;

    String PasswordHolder, EmailHolder,finalResult;
    Boolean CheckEditText ;

    String HttpURL = BASE_URL+"UserLogin.php";
    HttpParse httpParse = new HttpParse();

    ProgressDialog progressDialog;

    HashMap<String,String> hashMap = new HashMap<>();

    public static final String UserEmail = "";
//    protected Spinner type ;
    String[] items = new String[]{"User", "Teacher"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.pass);
        LogIn = (Button)findViewById(R.id.Login);
        register = (Button)findViewById(R.id.toRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Login.this,user_register.class);
                startActivity(intent);

            }
        });
//        type = (Spinner)findViewById(R.id.editTextType);


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
//        type.setAdapter(adapter);


        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

//                    UserLoginFunction(EmailHolder, PasswordHolder,type.getSelectedItem().toString());
                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {

                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exiting")
                .setMessage("Are you sure you want Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }




    public void CheckEditTextIsEmptyOrNot(){
        try{
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }catch (Exception e)
    {
        e.getMessage();
    }
    }

    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Login.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                Log.d("response",httpResponseMsg);
                try{
                progressDialog.dismiss();

                    String message = httpResponseMsg.substring(0, httpResponseMsg.indexOf("."));
                    String id = httpResponseMsg.substring(httpResponseMsg.indexOf(".") + 1, httpResponseMsg.indexOf(","));
                    String userRole = httpResponseMsg.substring(httpResponseMsg.indexOf(",") + 1, httpResponseMsg.length());
                    Log.d("response",   "   .....  " + message + "    ....... " + id+"......."+userRole);

                    if (message.equalsIgnoreCase("Data Matched")) {

                        finish();
                        Intent intent;
                        Users.email = email;
                        Users.user_id = Integer.valueOf(id);
                        Users.type = userRole;
                        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                        if (userRole.equalsIgnoreCase("Teacher")) {
                            intent = new Intent(Login.this, Dashboard.class);
                            intent.putExtra(UserEmail, email);
                            startActivity(intent);
                        } else if (userRole.equalsIgnoreCase("User")) {
                            intent = new Intent(Login.this, Dashboard.class);
                            intent.putExtra(UserEmail, email);
                            startActivity(intent);
                        }
                    } else {

                        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    Log.d("Error2",e.getMessage());
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();
        try {
            Log.d("Error",email);
            userLoginClass.execute(email, password);
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

}