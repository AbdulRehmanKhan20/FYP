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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import sc.example.com.comsats.Model.ChatAdapter;
import sc.example.com.comsats.Model.CheckNetworkStatus;
import sc.example.com.comsats.Model.HttpJsonParser;
import sc.example.com.comsats.Model.Users;

public class aChatRoom extends URL {
    final Context context = this;
    HashMap<String,String> hashMap = new HashMap<>();
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_ROOM_ID = "id";
    private static final String KEY_ROOM_NAME = "name";
    private ArrayList<HashMap<String, String>> chatRoomList;
    private GridView chatRoomListView;
    Button btnlogout,btn_addRoom;
    private ProgressDialog pDialog;
    ProgressDialog progressDialog;
    String finalResult;
    String HttpURL = BASE_URL+"fetch_user_rooms.php";
    String HttpURL1 = BASE_URL+"add_Room.php";
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_chat_room);

        getSupportActionBar().hide();

        btn_addRoom = (Button)  findViewById(R.id.btn_addRoom);
// add button listener
        btn_addRoom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try{
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Add Room");

                // set the custom dialog components - text, image and button
                final EditText etName = (EditText) dialog.findViewById(R.id.etName);
                final EditText etDescription = (EditText) dialog.findViewById(R.id.etDescription);

                Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
                // if button is clicked, close the custom dialog
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        addRoom(etName.getText().toString(),etDescription.getText().toString());
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
                    Intent i = new Intent(aChatRoom.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                } else {
                    Toast.makeText(aChatRoom.this,
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }

            }catch (Exception e)
            {
                e.getMessage();
            }

            }
        });
        try{
        chatRoomListView = (GridView) findViewById(R.id.chatRoomList);
        new FetchChatRoom().execute();
        }catch (Exception e)
        {
            e.getMessage();
        }

    }

    /**
     * Fetches the list of hospitals from the server
     */
    public class FetchChatRoom extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            try{
            pDialog = new ProgressDialog(aChatRoom.this);
            pDialog.setMessage("Loading Chat Room. Please wait...");
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
                    BASE_URL + "fetch_all_rooms.php", "GET", null);

                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONArray rooms;
                if (success == 1) {
                    chatRoomList = new ArrayList<>();
                    rooms = jsonObject.getJSONArray("data");
                    //Iterate through the response and populate hospitals list
                    for (int i = 0; i < rooms.length(); i++) {
                        Log.d("Rooms",String.valueOf(i));
                        JSONObject room = rooms.getJSONObject(i);
                        Integer roomId = room.getInt("id");
                        String roomName = room.getString("name");
                        String count = room.getString("count");

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_ROOM_ID, roomId.toString());
                        map.put(KEY_ROOM_NAME, roomName);
                        map.put("count", count);
                        chatRoomList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            try{
            pDialog.dismiss();
                    populateHospitalList();
            }catch (Exception e)
            {
                e.getMessage();
            }
        }

    }

    /**
     * Updating parsed JSON data into ListView
     * */
    private void populateHospitalList() {
        try {
            ChatAdapter adapter = new ChatAdapter(
                    aChatRoom.this, chatRoomList,
                    R.layout.list_item2, new String[]{"id",
                    "name", "count"},
                    new int[]{ R.id.roomId,R.id.roomName, R.id.count});

            // updating listview
            chatRoomListView.setAdapter(adapter);
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


    public void addRoom(final String name, final String description) {

        class addRoomClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                try{
                progressDialog = ProgressDialog.show(aChatRoom.this, "Loading Data", null, true, true);
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
                    Toast.makeText(aChatRoom.this, message, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("name", params[0]);
                hashMap.put("description", params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL1);

                return finalResult;
            }
        }

        addRoomClass addRoomC = new addRoomClass();

        try {
            addRoomC.execute(name,description).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
