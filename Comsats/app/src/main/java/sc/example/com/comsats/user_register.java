package sc.example.com.comsats;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;

public class user_register extends URL {

    Button register, log_in;
    EditText username, Email, Password,firstName,lastName ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder,FirstHolder,LastHolder;
    String finalResult ;
    String HttpURL = BASE_URL+"UserRegistration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    Spinner type ;
    //create a list of items for the spinner.
    String[] items = new String[]{ "User","Teacher"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        getSupportActionBar().hide();
        //Assign Id'S
        username = (EditText)findViewById(R.id.editTextUsername);
        type = (Spinner)findViewById(R.id.editTextType);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);
        firstName = (EditText)findViewById(R.id.editTextFirstName);
        lastName = (EditText)findViewById(R.id.editTextLastName);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        type.setAdapter(adapter);


        register = (Button)findViewById(R.id.Submitt);
        log_in = (Button)findViewById(R.id.Login);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(F_Name_Holder,L_Name_Holder, EmailHolder, PasswordHolder,FirstHolder,LastHolder);
                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(user_register.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }catch (Exception e)
            {
                e.getMessage();
            }
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(user_register.this,Login.class);
                startActivity(intent);

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

        F_Name_Holder = this.username.getText().toString();
        L_Name_Holder = this.type.getSelectedItem().toString();
        EmailHolder = this.Email.getText().toString();
        PasswordHolder = this.Password.getText().toString();
        FirstHolder = this.firstName.getText().toString();
        LastHolder = this.lastName.getText().toString();


        if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)|| TextUtils.isEmpty(FirstHolder)|| TextUtils.isEmpty(LastHolder))
        {
            CheckEditText = false;

        }
        else {
            Toast.makeText(user_register.this,F_Name_Holder+"  "+L_Name_Holder+"  "+EmailHolder+"   "+PasswordHolder+"   "+FirstHolder+"   "+LastHolder,Toast.LENGTH_SHORT).show();

            CheckEditText = true ;
        }
        }catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password,final String firstH, final String lastH){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(user_register.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                try {
                    progressDialog.dismiss();

                    Toast.makeText(user_register.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();


                    finish();
                    Intent i = new Intent(user_register.this, Login.class);
                    startActivity(i);
                }catch (Exception e)
                {
                    e.getMessage();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0]);

                hashMap.put("type",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("password",params[3]);

                hashMap.put("firstName",params[4]);

                hashMap.put("lastName",params[5]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();
        try{
        userRegisterFunctionClass.execute(F_Name_Holder,L_Name_Holder,email,password,FirstHolder,LastHolder);
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

}

