package silo.com.silo.UI.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;
import silo.com.silo.UI.Controller.ApiClient;
import silo.com.silo.UI.Controller.LandMark;
import silo.com.silo.UI.Controller.LandMarkList;
import silo.com.silo.UI.Controller.Post;
import silo.com.silo.UI.Controller.PostList;

public class FragmentLandMark extends Fragment {
    View v;
    private RecyclerView rview;
    private AdapterLandMark adapter;
    private RecyclerView.LayoutManager layout;
    private List<LandMarkList> landMarkList;
    private AdapterLandMark feedAdapter;
    private List<LandMark> LandMarkBundleFull;
    private LandMarkList landMarkList1;

    private ImageView emptyLogo;
    private TextView emptyText;
    private RotateLoading rotateLoading;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_landmark,container,false);
        rview = v.findViewById(R.id.landmark_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        RelativeLayout relativeLayout = getActivity().findViewById(R.id.relativeLayout);

        relativeLayout.setGravity(Gravity.CENTER);

        emptyLogo = v.findViewById(R.id.emptyLogo);
        emptyText = v.findViewById(R.id.emptyText);
        rotateLoading = v.findViewById(R.id.rotateloading);

        rotateLoading.start();

        ImageView back = (ImageView) getActivity().findViewById(R.id.content_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView contentHamburger = (ImageView) getActivity().findViewById(R.id.content_hamburger);
                contentHamburger.setVisibility(View.VISIBLE);
                ImageView back2 = (ImageView) getActivity().findViewById(R.id.content_back);
                back2.setVisibility(View.GONE);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentplace, new FragmentBeranda(), "NewFragmentTag");
                TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
                title.setText("SILO");
                ft.commit();
            }
        });

        landMarkList = new ArrayList<>();
        feedAdapter = new AdapterLandMark(landMarkList1,this.getContext());

        String category = getArguments().getString("category");
//        Toast.makeText(getContext(),"cek : " + myStr, Toast.LENGTH_SHORT).show();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://silo.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<LandMarkList> landMarkGet = null;

        if(category.equalsIgnoreCase("Penginapan"))
        {
             landMarkGet = apiClient.getPenginapan();
        }
        else if(category.equalsIgnoreCase("Restoran"))
        {
            landMarkGet = apiClient.getRestoran();
        }
        else if(category.equalsIgnoreCase("Kesehatan"))
        {
            landMarkGet = apiClient.getKesehatan();
        }
        else if(category.equalsIgnoreCase("InfoPasar"))
        {
            landMarkGet = apiClient.getInfoPasar();
        }

        landMarkGet.enqueue(new Callback<LandMarkList>() {
            @Override
            public void onResponse(Call<LandMarkList> call, Response<LandMarkList> response) {
                try {
                    if(!response.body().getData().isEmpty()) {
                        adapter = new AdapterLandMark(response.body(), getContext());
                        LandMarkBundleFull = response.body().getData();
                        rview.setAdapter(adapter);

                        rotateLoading.stop();
                        rotateLoading.setVisibility(View.GONE);
                        RelativeLayout relativeLayout = getActivity().findViewById(R.id.relativeLayout);

                        relativeLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    else {
                        rotateLoading.stop();
                        rotateLoading.setVisibility(View.GONE);
                        emptyText.setVisibility(View.VISIBLE);
                        emptyLogo.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    rotateLoading.stop();
                    rotateLoading.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Tidak Ada Posting!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LandMarkList> call, Throwable t) {
//                rotateLoading.stop();
//                rotateLoading.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    // replace your fragment here
                    final ImageView contentHamburger = (ImageView) getActivity().findViewById(R.id.content_hamburger);
                    contentHamburger.setVisibility(View.VISIBLE);
                    ImageView back2 = (ImageView) getActivity().findViewById(R.id.content_back);
                    back2.setVisibility(View.GONE);
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentplace, new FragmentBeranda(), "NewFragmentTag");
                    TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
                    title.setText("SILO");
                    ft.commit();

                    return true;

                }

                return false;
            }
        });
    }
}
