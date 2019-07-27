package silo.com.silo.UI.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import silo.com.silo.R;

public class Register extends AppCompatActivity {

    ImageView back;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Register.this, Start.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        back = findViewById(R.id.btnBack);
        login = findViewById(R.id.btnSudah);
    }
}
