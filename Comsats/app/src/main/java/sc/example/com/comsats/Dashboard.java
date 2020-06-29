package sc.example.com.comsats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Button;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    Button btnLogout,btn_reserveAuditorium,btn_DayCareReserve,btn_journal,btn_chatRoom,btn_commentOnJournal,btnAlumni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        btn_reserveAuditorium = (Button) findViewById(R.id.btn_reserveAuditorium);
        btn_DayCareReserve = (Button) findViewById(R.id.btn_DayCareReserve);
        btn_journal =(Button) findViewById(R.id.btn_journal);
        btn_chatRoom = (Button) findViewById(R.id.btn_chatRoom);
        btn_commentOnJournal =(Button) findViewById(R.id.btn_commentOnJournal);
        btnAlumni =(Button) findViewById(R.id.btnAlumni);
        btnLogout =(Button) findViewById(R.id.btnLogout);

        btn_reserveAuditorium.setOnClickListener(this);
        btn_DayCareReserve.setOnClickListener(this);
        btn_journal.setOnClickListener(this);
        btn_chatRoom.setOnClickListener(this);
        btn_commentOnJournal.setOnClickListener(this);
        btnAlumni.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btn_reserveAuditorium:
                // Do something
                startActivity(new Intent(Dashboard.this,aAuditorium.class));
                break;
            case R.id.btn_DayCareReserve:
                // Do something
                startActivity(new Intent(Dashboard.this,aReserveDayCare.class));
                break;
            case R.id.btn_journal:
                // Do something
                startActivity(new Intent(Dashboard.this,aJournal.class));
                break;
            case R.id.btn_chatRoom:
                // Do something
                startActivity(new Intent(Dashboard.this,aChatRoom.class));
                break;
            case R.id.btn_commentOnJournal:
                // Do something
                startActivity(new Intent(Dashboard.this,aCommentOnJournal.class));
                break;
            case R.id.btnAlumni:
                // Do something
                startActivity(new Intent(Dashboard.this,aAlumni.class));
                break;
            case R.id.btnLogout:
                // Do something
                finish();
                startActivity(new Intent(Dashboard.this,Login.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
        }
    }
}
