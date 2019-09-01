package silo.com.silo.UI.UI;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;
import silo.com.silo.UI.Controller.ApiClient;
import silo.com.silo.UI.Controller.CommentList;
import silo.com.silo.UI.Controller.Comment;

public class UserComment extends AppCompatActivity {

    RecyclerView rview;
    private AdapterComment adapter;
    private TextView content,title,emptyText;
    private SearchView search;
    private RecyclerView.LayoutManager layout;
    private ImageView backButton,photo;
    private List<Comment> CustomerBundleFull;
    private List<Comment> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_comment);
        init();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("desc"));
        Picasso.get().load("http://silo.yafetrakan.com/images/"+getIntent().getStringExtra("photo")).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(photo);

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://silo.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<CommentList> commentGet = apiClient.getComment(getIntent().getIntExtra("id",-1));

        commentGet.enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                try {
                    if(!response.body().getData().isEmpty()) {
                        adapter = new AdapterComment(response.body().getData(), UserComment.this);
                        CustomerBundleFull = response.body().getData();
                        rview.setAdapter(adapter);
                    }
                    else {
                        emptyText.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    emptyText.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Tidak Ada Comment!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void init(){
        rview = findViewById(R.id.comment_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        content = findViewById(R.id.post_content);
        title = findViewById(R.id.post_title);
        photo = findViewById(R.id.post_photo);
        backButton = findViewById(R.id.content_back);
        emptyText = findViewById(R.id.emptyText);
    }
}
