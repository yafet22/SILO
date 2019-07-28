package silo.com.silo.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;

public class FragmentBeranda extends Fragment {
    View v;
    private RecyclerView rview;
//    private AdapterBeranda adapter;
    private RecyclerView.LayoutManager layout;
//    private List<CustomerList> customerList;
//    private AdapterBeranda adapterBeranda;
//    private List<Customer> CustomerBundleFull;
//    private AdapterBeranda berandaAdapter;
//    private CustomerList customerList1;
    FrameLayout hotel;
    RelativeLayout.LayoutParams fragmentparams;
    View fragmentlayout;
    TextView title;



//    SessionManager session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


//
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        super.onCreateOptionsMenu(menu,inflater);
//        inflater.inflate(R.menu.menu, menu);
//
//        MenuItem searchCustomer = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchCustomer.getActionView();
//
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                newText = newText.toLowerCase();
//                List<Customer> newList = new ArrayList<>();
//                //Sparepart newList = new Sparepart();
//
//                for (Customer customer : CustomerBundleFull)
//                {
//                    String alamat = customer.getCustomerAddress().toLowerCase();
//
//                    String name = customer.getCustomerName().toLowerCase();
//                    Log.d("customerlower",customer.getCustomerName().toLowerCase());
//                    if(name.contains(newText) || alamat.contains(newText))
//                        newList.add(customer);
//                }
//                adapter.setFilter(newList);
//
//                return false;
//            }
//        });
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_beranda,container,false);
        hotel = v.findViewById(R.id.Hotel);

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentplace, new FragmentLandMark(), "NewFragmentTag");
                TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
                title.setText("Hotel");
                final ImageView contentHamburger = (ImageView) getActivity().findViewById(R.id.content_hamburger);
                contentHamburger.setVisibility(View.GONE);

                ft.commit();
            }
        });
//        rview = v.findViewById(R.id.customer_list);
//        rview.setHasFixedSize(true);
//        layout = new LinearLayoutManager(getContext());
//        rview.setLayoutManager(layout);

//        customerList = new ArrayList<>();
//        berandaAdapter = new AdapterBeranda(customerList1,this.getContext());
//
//        session = new SessionManager(getContext());
//        session.checkLogin();
//
//        Retrofit retrofit= new retrofit2.Retrofit.Builder()
//                .baseUrl("http://192.168.19.140/8991/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiClient apiClient = retrofit.create(ApiClient.class);
//
//        Call<CustomerList> customerGet = apiClient.getCustomer();
//
//        customerGet.enqueue(new Callback<CustomerList>() {
//            @Override
//            public void onResponse(Call<CustomerList> call, Response<CustomerList> response) {
//                try {
//                    adapter = new AdapterBeranda(response.body(),getContext());
//                    CustomerBundleFull =  response.body().getData();
//
//                    Collections.sort(CustomerBundleFull, new Comparator<Customer>() {
//                        @Override
//                        public int compare(Customer o1, Customer o2) {
//                            return o1.getCustomerName().toLowerCase().compareTo(o2.getCustomerName().toLowerCase());
//                        }
//                    });
//
//                    //                   adapter.notifyDataSetChanged();
////                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
////                    rview.setLayoutManager(mLayoutManager);
////                    rview.setItemAnimator(new DefaultItemAnimator());
//                    rview.setAdapter(adapter);
//                } catch (Exception e) {
//                    Toast.makeText(getContext(), "Tidak Ada Customer!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CustomerList> call, Throwable t) {
//                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
//            }
//
//        });


        return v;
    }

}