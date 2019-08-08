package silo.com.silo.UI.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;
import silo.com.silo.UI.Controller.ApiClient;
import silo.com.silo.UI.Controller.Post;
import silo.com.silo.UI.Controller.PostList;
import silo.com.silo.UI.Session.SessionManager;

public class FragmentPertanian extends Fragment {
    View v;
    private RecyclerView rview;
    private AdapterFeed adapter;
    private RecyclerView.LayoutManager layout;
    private List<PostList> postList;
    private AdapterFeed feedAdapter;
    private List<Post> PostBundleFull;
    private PostList postList1;

    SessionManager session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_pertanian,container,false);
        rview = v.findViewById(R.id.pertanian_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        postList = new ArrayList<>();
        feedAdapter = new AdapterFeed(postList1,this.getContext());

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://silo.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<PostList> supplierGet = apiClient.getPosts();

        supplierGet.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                try {
                    adapter = new AdapterFeed(response.body(),getContext());
                    PostBundleFull =  response.body().getData();
                    //                   adapter.notifyDataSetChanged();
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                    rview.setLayoutManager(mLayoutManager);
//                    rview.setItemAnimator(new DefaultItemAnimator());
                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Tidak Ada Posting!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });


        return v;
    }
}
