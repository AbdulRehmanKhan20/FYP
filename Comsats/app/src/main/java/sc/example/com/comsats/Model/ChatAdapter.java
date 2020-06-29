package sc.example.com.comsats.Model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import sc.example.com.comsats.Dashboard;
import sc.example.com.comsats.HttpParse;
import sc.example.com.comsats.R;
import sc.example.com.comsats.aChatChat;
import sc.example.com.comsats.aChatRoom;

import static sc.example.com.comsats.URL.BASE_URL;

public class ChatAdapter extends SimpleAdapter {
    ProgressDialog progressDialog;
    String finalResult;
    String HttpURL = BASE_URL+"fetch_user_rooms.php";
    String updateURL = BASE_URL+"update_Room.php";
    String HttpURL1 = BASE_URL+"joinRoom.php";
    HttpParse httpParse = new HttpParse();
    private ArrayList<HashMap<String, String>> chatRoomList;
    HashMap<String,String> hashMap = new HashMap<>();

    private static final String KEY_ROOM_ID = "id";
    private static final String KEY_ROOM_NAME = "name";
    Context context;
    public ChatAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        try{
        TextView tv = (TextView) view.findViewById(R.id.roomId);
            final String room_id = ((TextView) view.findViewById(R.id.roomId))
                    .getText().toString();
            Button btn_open = view.findViewById(R.id.btn_open);
            Button btn_leave = view.findViewById(R.id.btn_leave);

            Log.d("roomId", room_id);


            boolean result =false;
            result = checkUser(room_id);
            if (result) {
                btn_open.setText("Enter Room");
                btn_open.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,
                                aChatChat.class);
                        intent.putExtra(KEY_ROOM_ID, room_id);
                        context.startActivity(intent);
                    }
                });

                btn_leave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateRoom(String.valueOf(Users.user_id),room_id);
                        ((Activity) context).finish();
                        context.startActivity(((Activity) context).getIntent());
                    }
                });
            }

            else {
                btn_open.setText("JOIN ROOM");
                btn_open.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinRoom(String.valueOf(Users.user_id),room_id);
                        ((Activity) context).finish();
                        context.startActivity(((Activity) context).getIntent());
                                Intent intent = new Intent(context,
                                        aChatChat.class);
                        intent.putExtra(KEY_ROOM_ID, room_id);
                        context.startActivity(intent);
                    }
                });
                btn_leave.setEnabled(false);
            }
        }catch (Exception e)
        {
            e.getMessage();
        }

        return view;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public boolean checkUser(final String roomId){

        class checkUserDetailsClass extends AsyncTask<String,Void,String> {
            boolean result =false;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(final String httpResponseMsg) {
                 super.onPostExecute(httpResponseMsg);
                 Log.d("result", httpResponseMsg);
            }

            @Override
            protected String doInBackground(String... params) {
                //progressDialog.dismiss();
                hashMap.put("roomid",params[0]);
                hashMap.put("userid",String.valueOf(Users.user_id));
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                result = Boolean.valueOf(finalResult);
                return finalResult;
            }
        }
        checkUserDetailsClass userLoginClass = new checkUserDetailsClass();
        try {
            userLoginClass.execute(roomId).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLoginClass.result;
    }


    public void updateRoom(final String user_id, final String room_id ) {

        class RoomUpdateClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
//                progressDialog.dismiss();

                try {
                    String message = httpResponseMsg;
                    Log.d("response", "   .....  " + message);

                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("userid", params[0]);
                hashMap.put("roomid", params[1]);
                finalResult = httpParse.postRequest(hashMap, updateURL);
                return finalResult;
            }
        }

        RoomUpdateClass updateR = new RoomUpdateClass();

        try {
            updateR.execute(user_id,room_id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void joinRoom(final String user_id, final String room_id ) {

        class joinRoomClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
//                progressDialog.dismiss();

                try {
                    String message = httpResponseMsg;
                    Log.d("response", "   .....  " + message);

                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("userid", params[0]);
                hashMap.put("roomid", params[1]);
                finalResult = httpParse.postRequest(hashMap, HttpURL1);
                return finalResult;
            }
        }
        joinRoomClass joinRoom = new joinRoomClass();
        try {
            joinRoom.execute(user_id,room_id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

