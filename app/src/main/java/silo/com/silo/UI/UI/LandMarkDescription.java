package silo.com.silo.UI.UI;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import silo.com.silo.R;

public class LandMarkDescription extends AppCompatActivity {

    TextView landMarkName,desc;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mark_description);
        init();

        ImageView back = (ImageView) findViewById(R.id.content_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        landMarkName.setText(getIntent().getStringExtra("name"));
        desc.setText(getIntent().getStringExtra("desc"));

        Picasso.get().load("http://silo.yafetrakan.com/images/"+getIntent().getStringExtra("photo")).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(photo);

    }

    public void init()
    {
        landMarkName = findViewById(R.id.tvName);
        desc = findViewById(R.id.tvDesc);
        photo = findViewById(R.id.photoLandMark);
    }
}
