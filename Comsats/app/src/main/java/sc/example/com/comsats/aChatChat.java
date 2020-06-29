package sc.example.com.comsats;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import sc.example.com.comsats.Model.ChatArrayAdapter;
import sc.example.com.comsats.Model.HttpJsonParser;
import sc.example.com.comsats.Model.Users;

import static sc.example.com.comsats.URL.BASE_URL;


public class aChatChat extends Activity {
    private static final String TAG = "ChatActivity";
    HttpParse httpParse = new HttpParse();
    String HttpURL = BASE_URL+"send_message.php";
    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    String User = "", chatTextMessage = "";
    private boolean side = false;
    String finalResult;
    private ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> fetchMessages = new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a_chat_chat);

        try {
            fetchMessages = new FetchChatRoom(getIntent().getStringExtra("id")).execute().get();
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

        buttonSend = (Button) findViewById(R.id.send);

        listView = (ListView) findViewById(R.id.msgview);

        try{
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);


        chatText = (EditText) findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
                insertMessage(chatTextMessage,getIntent().getStringExtra("id"),String.valueOf(Users.user_id));
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        }catch (Exception e)
        {
            e.getMessage();
        }


        try{
        for (int i = 0; i < fetchMessages.size(); i++) {
            String userId = fetchMessages.get(i).get("userId");
            String username = fetchMessages.get(i).get("username");
            String message = fetchMessages.get(i).get("message");

            if (userId.equalsIgnoreCase(String.valueOf(Users.user_id))) {
                side = false;
                User = username;
                chatTextMessage = message;
                sendChatMessage();
            } else {
                side = true;
                User = username;
                chatTextMessage = message;
                sendChatMessage();
            }
        }
        }catch (Exception e)
        {
            e.getMessage();
        }

        try{
        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

    private boolean sendChatMessage() {
        try{
        if (!chatText.getText().toString().isEmpty()) {
            chatTextMessage = chatText.getText().toString();
            side = false;
        }
        chatArrayAdapter.add(new ChatMessage(side, chatTextMessage, User));

        chatText.setText("");
        side = !side;
        }catch (Exception e)
        {
            e.getMessage();
        }
        return true;
    }


    public void insertMessage(final String name, final String room_id, final String user_id) {

        class sendMessageClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(aChatChat.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();

                try {
                    String message = httpResponseMsg;
                    Log.d("response", "   .....  " + message);
                    Toast.makeText(aChatChat.this, message, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("message", params[0]);

                hashMap.put("room_id", params[1]);

                hashMap.put("user_id", params[2]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        sendMessageClass sendMessage = new sendMessageClass();

        try {
            sendMessage.execute(name,room_id,user_id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




class FetchChatRoom extends AsyncTask<String, String, ArrayList<HashMap<String, String>>> {
    private static final String KEY_SUCCESS = "success";
    private ArrayList<HashMap<String, String>> messageList;
    String roomId ;
    public FetchChatRoom(String roomid)
    {
        this.roomId = roomid;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public ArrayList<HashMap<String, String>> doInBackground(String... params) {
        HttpJsonParser httpJsonParser = new HttpJsonParser();
        Map<String, String> parames = new HashMap<>();
        parames.put("roomId",roomId);

        try{
        JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "fetch_all_messages.php", "GET", parames);

            int success = jsonObject.getInt(KEY_SUCCESS);
            JSONArray messages;
            if (success == 1) {
                messageList = new ArrayList<>();
                messages = jsonObject.getJSONArray("data");
                //Iterate through the response and populate hospitals list
                for (int i = 0; i < messages.length(); i++) {
                    JSONObject message = messages.getJSONObject(i);

                    String messageId = message.getString("userId");
                    String messageUserName = message.getString("username");
                    String messageContent = message.getString("message");

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("userId", messageId.toString());
                    map.put("username", messageUserName.toString());
                    map.put("message", messageContent.toString());
                    messageList.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
//            runOnUiThread(new Runnable() {
//                public void run() {

        Log.d("Resulttttt",result.toString());

//                }
//            });
    }

}