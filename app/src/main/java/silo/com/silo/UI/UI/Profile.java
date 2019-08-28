package silo.com.silo.UI.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import silo.com.silo.R;
import silo.com.silo.UI.Session.SessionManager;

public class Profile extends AppCompatActivity {

    TextView topName;
    EditText name,phoneNumber;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        session = new SessionManager(Profile.this);
        name.setText(session.getKeyName());
        topName.setText(session.getKeyName());
        phoneNumber.setText(session.getKeyPhone());
    }

    private void init(){
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        topName = findViewById(R.id.topname);
    }
}
