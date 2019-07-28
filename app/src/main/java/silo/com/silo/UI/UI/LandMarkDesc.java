package silo.com.silo.UI.UI;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import silo.com.silo.R;

public class LandMarkDesc extends AppCompatActivity {

    TextView title,comment;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mark_desc);
        init();
        title.setText(getIntent().getStringExtra("name"));
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(LandMarkDesc.this, Comment.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                startActivity(intent);
            }
        });

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
    }

    private void init(){
        title = findViewById(R.id.toolbar_title);
        comment = findViewById(R.id.comment);
        back = findViewById(R.id.content_back);
    }
}
