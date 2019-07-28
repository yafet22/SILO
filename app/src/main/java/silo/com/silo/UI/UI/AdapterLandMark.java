package silo.com.silo.UI.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;

public class AdapterLandMark extends RecyclerView.Adapter<AdapterLandMark.MyViewHolder> {

    private ArrayList<String> rvData;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle,tvSubtitle;
        public RatingBar rating;
        public RelativeLayout wrapper;
        public MyViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_title);
            rating = v.findViewById(R.id.rating);
            wrapper = v.findViewById(R.id.landmark);
        }
    }

    public AdapterLandMark(ArrayList<String> inputData, Context context) {
        this.rvData = inputData;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterLandMark.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_landmark, viewGroup, false);
        return new MyViewHolder(v);
    }



//    public void filter(String charText) {
//        Log.d( "filter: ", charText);
//
//        charText = charText.toLowerCase(Locale.getDefault());
//        CustomerFilter.clear();
//        if (charText.length() == 0) {
//            CustomerFilter.addAll(CustomerBundle);
//        }
//        else
//        {
//            for (Customer obj : CustomerBundle) {
//                if (obj.getCustomerName().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    CustomerFilter.add(obj);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    CustomerFilter = CustomerBundle;
//                } else {
//                    List<Customer> filteredList = new ArrayList<>();
//                    for (Customer obj : CustomerBundle) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (obj.getCustomerName().toLowerCase().contains(charString.toLowerCase())
//                                || obj.getCustomerAddress().toLowerCase().contains(charString.toLowerCase())
//                                || obj.getCustomerPhoneNumber().toLowerCase().contains(charString.toLowerCase())){
//                            filteredList.add(obj);
//                        }
//                    }
//                    CustomerFilter = filteredList;
//
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = CustomerFilter;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                CustomerFilter = (ArrayList<Customer>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    @Override
    public void onBindViewHolder(@NonNull  final AdapterLandMark.MyViewHolder vh, final int i) {
        final String name = rvData.get(i);
        vh.tvTitle.setText(rvData.get(i));
        vh.rating.setRating(3);;
        vh.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, LandMarkDesc.class);
                intent.putExtra("name",rvData.get(i));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return rvData.size();
    }

}


