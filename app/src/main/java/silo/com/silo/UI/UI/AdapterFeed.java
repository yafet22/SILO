package silo.com.silo.UI.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silo.com.silo.R;
import silo.com.silo.UI.Controller.ApiClient;
import silo.com.silo.UI.Controller.CommentData;
import silo.com.silo.UI.Controller.Post;
import silo.com.silo.UI.Controller.PostList;
import silo.com.silo.UI.Session.SessionManager;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder> {
    private Context mContext;
    private PostList PostBundle;
    SessionManager session;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, description, category,see_comment,btnComment;
        EditText comment;
        CardView cardView;
        ImageView photo;

        public LinearLayout topcard;

        public  MyViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.post_title);
            description = (TextView) itemView.findViewById(R.id.post_description);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            photo = (ImageView) itemView.findViewById(R.id.post_photo);
            topcard = itemView.findViewById(R.id.topcard);
            see_comment = (TextView) itemView.findViewById(R.id.see_comment);
            comment = (EditText) itemView.findViewById(R.id.usercomment);
            btnComment = (TextView) itemView.findViewById(R.id.btnSendComment);


/*            ImageView sparepart_img;
            sparepart_img = (ImageView) itemView.findViewById(R.id.sparepart_image); */
        }
    }

    public AdapterFeed(PostList PostBundle, Context mContext) {
        this.mContext = mContext;
        this.PostBundle = PostBundle;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycle_feeds, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterFeed.MyViewHolder myViewHolder, final int i) {

        final Post data = PostBundle.getData().get(i);
        final int ifinal = myViewHolder.getAdapterPosition();

        myViewHolder.title.setText(data.getTitle());
        myViewHolder.description.setText(data.getDescription());
        Picasso.get().load("http://silo.yafetrakan.com/images/"+data.getPhoto()).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(myViewHolder.photo);

        myViewHolder.see_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserComment.class);
                intent.putExtra("id", data.getId());
                intent.putExtra("desc",data.getDescription());
                mContext.startActivity(intent);
            }
        });

        session = new SessionManager(mContext);

        myViewHolder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.isLoggedIn())
                {

                    if(!myViewHolder.comment.getText().toString().isEmpty() && session.getKeyStatus().equalsIgnoreCase("verified")) {
                        try {
                            final ProgressDialog progressDialog = new ProgressDialog(mContext, R.style.AppTheme_Dark_Dialog);
                            progressDialog.setMessage("Memproses Comment");
                            progressDialog.show();

                            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                                    .baseUrl("http://silo.yafetrakan.com/api/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            ApiClient apiClient = retrofit.create(ApiClient.class);

                            Call<CommentData> addComment = apiClient.addComment(myViewHolder.comment.getText().toString(),data.getId(),Integer.parseInt(session.getKeyId()));

                            addComment.enqueue(new Callback<CommentData>() {
                                @Override
                                public void onResponse(Call<CommentData> call, Response<CommentData> response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(mContext, "Berhasil Input Comment", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, UserComment.class);
                                    intent.putExtra("id", data.getId());
                                    intent.putExtra("desc",data.getDescription());
                                    mContext.startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<CommentData> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(mContext, "Gagal Input Comment", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if(!session.getKeyStatus().equalsIgnoreCase("verified")) {
                        Toast.makeText(mContext, "Akun anda belum terverifikasi", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(mContext, "Anda harus login untuk dapat berkomentar", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        myViewHolder.topcard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, AddSparepart.class);
//                intent.putExtra("simpan", i);
//                intent.putExtra("id", data.getIdSparepart());
//                intent.putExtra("name", data.getSparepartName());
//                intent.putExtra("merk", data.getMerk());
//                intent.putExtra("stock", data.getStock());
//                intent.putExtra("minstock", data.getMinStock());
//                intent.putExtra("purchaseprice", data.getPurchasePrice());
//                intent.putExtra("sellprice", data.getSellPrice());
//                intent.putExtra("placement", data.getPlacement());
//                intent.putExtra("position", data.getPosition());
//                intent.putExtra("place", data.getPlace()) ;
//                intent.putExtra("number", data.getNumber());
//                intent.putExtra("type", data.getSparepartTypeName());
//                intent.putExtra("idtype", data.getIdSparepartType());
//                intent.putExtra("image", data.getImage());
//                mContext.startActivity(intent);
//            }
//        });

//        myViewHolder.sparepart_name.setText(data.getSparepartName());
//        myViewHolder.stock.setText(data.getStock());
//        myViewHolder.sell_price.setText(data.getSellPrice());

    }

    @Override
    public int getItemCount() {
        return PostBundle.getData().size();
    }
}
