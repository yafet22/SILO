package silo.com.silo.UI.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import silo.com.silo.R;

public class Comment extends AppCompatActivity {

    TextView title;
    EditText comments;
    ImageView back;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        title.setText(getIntent().getStringExtra("name"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(LandMarkDesc.this, MainActivity.class);
//                intent.putExtra("addDialog", 0);
//                startActivity(intent);
//                FragmentManager fm = getFragmentManager();
//                fm.popBackStack();
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Comment.this, MainActivity.class);
                intent.putExtra("addDialog", 0);
                startActivity(intent);
            }
        });
    }

    private void init(){
        title = findViewById(R.id.toolbar_title);
        comments = findViewById(R.id.comments);
        back = findViewById(R.id.content_back);
        send = findViewById(R.id.btnSend);
    }
}
