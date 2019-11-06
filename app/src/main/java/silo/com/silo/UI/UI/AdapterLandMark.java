package silo.com.silo.UI.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
import silo.com.silo.UI.Controller.ApiClient;
import silo.com.silo.UI.Controller.CommentData;
import silo.com.silo.UI.Controller.LandMark;
import silo.com.silo.UI.Controller.LandMarkList;
import silo.com.silo.UI.Controller.Post;
import silo.com.silo.UI.Controller.PostList;
import silo.com.silo.UI.Session.SessionManager;

public class AdapterLandMark extends RecyclerView.Adapter<AdapterLandMark.MyViewHolder> {
    private Context mContext;
    private LandMarkList LandMarkBundle;
    SessionManager session;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, description, category;
        RelativeLayout landmark;
        ImageView photo;

        public LinearLayout topcard;

        public  MyViewHolder(View itemView){
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_title);
            landmark = (RelativeLayout) itemView.findViewById(R.id.landmark);
            photo = (ImageView) itemView.findViewById(R.id.previewImage);
        }
    }

    public AdapterLandMark(LandMarkList LandMarkBundle, Context mContext) {
        this.mContext = mContext;
        this.LandMarkBundle = LandMarkBundle;
    }

    @NonNull
    @Override
    public AdapterLandMark.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycle_landmark, viewGroup, false);

        return new AdapterLandMark.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterLandMark.MyViewHolder myViewHolder, final int i) {

        final LandMark data = LandMarkBundle.getData().get(i);
        final int ifinal = myViewHolder.getAdapterPosition();

        Picasso.get().load("http://siloselo.com/images/"+data.getPhoto()).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(myViewHolder.photo);
        myViewHolder.name.setText(data.getTitle());

        myViewHolder.landmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LandMarkDescription.class);
//                intent.putExtra("id", data.getId());
                intent.putExtra("desc",data.getDescription());
                intent.putExtra("name",data.getTitle());
                intent.putExtra("photo",data.getPhoto());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return LandMarkBundle.getData().size();
    }

}


