package sc.example.com.comsats.Model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.example.com.comsats.HttpParse;
import sc.example.com.comsats.R;
import sc.example.com.comsats.aChatChat;
import sc.example.com.comsats.aCommentOnJournal;

import static sc.example.com.comsats.URL.BASE_URL;

public class JournalAdapter extends SimpleAdapter {

    Context context;
    public JournalAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        try{
        final String journalId = ((TextView) view.findViewById(R.id.jId))
                .getText().toString();
            Button btnComment = view.findViewById(R.id.btnComment);
             btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                Intent inte= new Intent(context,aCommentOnJournal.class);
                inte.putExtra("journalId",journalId);
                context.startActivity(inte);
            }
        });
        final TextView journal = ((TextView) view.findViewById(R.id.body));
        journal.setText(Html.fromHtml(journal.getText().toString()));

            if(Users.type.equalsIgnoreCase("Teacher"))
            {
                btnComment.setVisibility(View.VISIBLE);
            }
            else if(Users.type.equalsIgnoreCase("User"))
            {
                btnComment.setVisibility(View.GONE);
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

}

