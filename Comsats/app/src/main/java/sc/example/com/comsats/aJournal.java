package sc.example.com.comsats;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


import com.onegravity.rteditor.RTEditText;
import com.onegravity.rteditor.RTManager;
import com.onegravity.rteditor.RTToolbar;
import com.onegravity.rteditor.api.RTApi;
import com.onegravity.rteditor.api.RTMediaFactoryImpl;
import com.onegravity.rteditor.api.RTProxyImpl;
import com.onegravity.rteditor.api.format.RTFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import sc.example.com.comsats.Model.CheckNetworkStatus;
import sc.example.com.comsats.Model.HttpJsonParser;
import sc.example.com.comsats.Model.JournalAdapter;
import sc.example.com.comsats.Model.Users;

public class aJournal extends URL {
    final Context context = this;
    HashMap<String,String> hashMap = new HashMap<>();
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_JOURNAL_ID = "id";
    private static final String KEY_JOURNAL_NAME = "name";
    private ArrayList<HashMap<String, String>> JournalList;
    private GridView JournalListView;
    Button btnlogout,btn_addJournal;
    private ProgressDialog pDialog;
    ProgressDialog progressDialog;
    String finalResult;
    String HttpURL1 = BASE_URL+"add_journal.php";
    HttpParse httpParse = new HttpParse();
    RTManager rtManager;
    RTApi rtApi;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        try {
            rtManager.onSaveInstanceState(outState);
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
        rtManager.onDestroy(isFinishing());
        }catch (Exception e)
        {
            e.getMessage();
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.RTE_ThemeLight );
        setContentView(R.layout.activity_a_journal);
        getSupportActionBar().hide();

        try{
        // create RTManager
         rtApi = new RTApi(this, new RTProxyImpl(this), new RTMediaFactoryImpl(this, true));
         rtManager = new RTManager(rtApi, savedInstanceState);
        }catch (Exception e)
        {
            e.getMessage();
        }
// register toolbar
        final ViewGroup toolbarContainer = (ViewGroup) findViewById(R.id.rte_toolbar_container);

        btn_addJournal = (Button)  findViewById(R.id.btn_addJournal);
        // add button listener
        btn_addJournal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try{
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.customjournal);
                dialog.setTitle("Add Journal");


                RTToolbar rtToolbar = (RTToolbar) dialog.findViewById(R.id.rte_toolbar);
                if (rtToolbar != null) {
                    rtManager.registerToolbar(toolbarContainer, rtToolbar);
                }

// register editor & set text
                final RTEditText etBody = (RTEditText) dialog.findViewById(R.id.etBody);
                rtManager.registerEditor(etBody, true);
                etBody.setRichTextEditing(true, "");
                // set the custom dialog components - text, image and button
                final EditText et_title = (EditText) dialog.findViewById(R.id.et_title);

                Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
                // if button is clicked, close the custom dialog
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        addJournal(et_title.getText().toString(),etBody.getText(RTFormat.HTML).toString());
                        finish();
                        startActivity(getIntent());
                    }
                });

                dialog.show();
            }catch (Exception e)
            {
                e.getMessage();
            }

            }});


        btnlogout = (Button)  findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    finish();
                    Intent i = new Intent(aJournal.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                } else {
                    Toast.makeText(aJournal.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }
                }catch (Exception e)
                {
                    e.getMessage();
                }

            }
        });
        try {
            JournalListView = (GridView) findViewById(R.id.JournalList);
            new FetchJournals().execute();
        }catch (Exception e)
        {
            e.getMessage();
        }

    }



    /**
     * Fetches the list of hospitals from the server
     */
    public class FetchJournals extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            try{
            pDialog = new ProgressDialog(aJournal.this);
            pDialog.setMessage("Loading Journals Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            }catch (Exception e)
            {
                e.getMessage();
            }
        }

        @Override
        public String doInBackground(String... params) {
            try{
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "fetch_all_journals.php", "GET", null);
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONArray Journals;
                if (success == 1) {
                    JournalList = new ArrayList<>();
                    Journals = jsonObject.getJSONArray("data");
                    //Iterate through the response and populate hospitals list
                    for (int i = 0; i < Journals.length(); i++) {
                        Log.d("Journals",String.valueOf(i));
                        JSONObject Journal = Journals.getJSONObject(i);
                        Integer jId = Journal.getInt("id");
                        String jTitle = Journal.getString("title");
                        String username = Journal.getString("username");
                        String body = Journal.getString("body");

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_JOURNAL_ID, jId.toString());
                        map.put(KEY_JOURNAL_NAME, jTitle);
                        map.put("username", username);
                        map.put("body", body);
                        JournalList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            try {
                pDialog.dismiss();
                populateJournalList();
            }catch (Exception e)
            {
                e.getMessage();
            }
        }

    }

    /**
     * Updating parsed JSON data into ListView
     * */

    // Teacher Will Comment on Journal here
    //Change the R.layout.list_item2

    private void populateJournalList() {
        try {
            JournalAdapter adapter = new JournalAdapter(
                    aJournal.this, JournalList,
                    R.layout.list_item, new String[]{"id",
                    "name", "username","body"},
                    new int[]{ R.id.jId,R.id.jTitle, R.id.username,R.id.body});

            // updating listview
            JournalListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch (Exception e)
        {
            Log.d("ExceptionError",e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 20) {
            // If the result code is 20 that means that
            // the user has deleted/updated the hospital.
            // So refresh the hospital listing
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }


    public void addJournal(final String name, final String description) {

        class addJournalClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    progressDialog = ProgressDialog.show(aJournal.this, "Loading Data", null, true, true);
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
                    Log.d("response", "   .....  " + message);
                    Toast.makeText(aJournal.this, message, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("title", params[0]);
                hashMap.put("body", params[1]);
                hashMap.put("author_id", String.valueOf(Users.user_id));

                finalResult = httpParse.postRequest(hashMap, HttpURL1);

                return finalResult;
            }
        }

        addJournalClass addJournalC = new addJournalClass();

        try {
            addJournalC.execute(name,description).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
