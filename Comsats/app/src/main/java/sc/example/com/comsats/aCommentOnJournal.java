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

public class aCommentOnJournal extends URL implements View.OnClickListener {
    private String finalResult,journalId,body,title;

    private EditText etBody;
    private Button addButton,logoutButton;

    private ProgressDialog progressDialog;


    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = BASE_URL+"add_teacherComment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_comment_on_journal);

        journalId = getIntent().getStringExtra("journalId");

        etBody = (EditText) findViewById(R.id.etBody);


        addButton = (Button) findViewById(R.id.btnAdd);
        logoutButton = (Button) findViewById(R.id.btnLogout);
        addButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLogout:
                try{
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    finish();
                    Intent i = new Intent(aCommentOnJournal.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                } else {
                    Toast.makeText(aCommentOnJournal.this,
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
                    addComment();

                } else {
                    Toast.makeText(aCommentOnJournal.this,
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


    private void addComment() {
        try {
            body = etBody.getText().toString();

            if (!TextUtils.isEmpty(body)) {
                insertComment(body);
            } else {
                Toast.makeText(aCommentOnJournal.this, "Please Fill the Fields", Toast.LENGTH_LONG).show();

            }
        }catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void insertComment( final String body){

        class CommentClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                try{
                progressDialog = ProgressDialog.show(aCommentOnJournal.this,"Loading Data",null,true,true);
                }catch (Exception e)
                {
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
                    Toast.makeText(aCommentOnJournal.this, message, Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(aCommentOnJournal.this,Dashboard.class));

                }catch (Exception e)
                {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("journalId",journalId);

                hashMap.put("body",params[1]);

                hashMap.put("user_id",String.valueOf(Users.user_id));

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        CommentClass comment = new CommentClass();

        try{
        comment.execute(journalId,body);
        }catch (Exception e)
        {
            e.getMessage();
        }
    }
}
