package sc.example.com.comsats;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);
        try {
            Thread td = new Thread() {

                public void run() {
                    try {
                        sleep(3000);
                    } catch (Exception e) {
                        e.getStackTrace();
                    } finally {
                        Intent intent = new Intent(Splash.this, user_register.class);
                        startActivity(intent);
                        finish();
                    }
                }

            };
            td.start();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }
}