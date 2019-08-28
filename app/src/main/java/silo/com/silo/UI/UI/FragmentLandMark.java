package silo.com.silo.UI.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;

public class FragmentLandMark extends Fragment {
    View v;
    private RecyclerView rview;
    private AdapterLandMark adapter;
    private RecyclerView.LayoutManager layout;
    private ArrayList<String> dataSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_landmark,container,false);
        rview = v.findViewById(R.id.landmark_list);
        dataSet = new ArrayList<>();
        initDataset();
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        adapter = new AdapterLandMark(dataSet,getContext());

        rview.setAdapter(adapter);

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

        return v;
    }

    private void initDataset(){

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Sahid Hotel");
        dataSet.add("Yellow Star");
        dataSet.add("Merapi Merbabu");
        dataSet.add("JW Marriot");
        dataSet.add("Harris");
        dataSet.add("Dafam");

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
