package silo.com.silo.UI.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import silo.com.silo.R;
import silo.com.silo.UI.Controller.Post;
import silo.com.silo.UI.Controller.PostList;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder> {
    private Context mContext;
    private PostList PostBundle;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, description, category;
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
    public void onBindViewHolder(@NonNull AdapterFeed.MyViewHolder myViewHolder, final int i) {

        final Post data = PostBundle.getData().get(i);
        final int ifinal = myViewHolder.getAdapterPosition();


        myViewHolder.title.setText(data.getTitle());
        myViewHolder.description.setText(data.getDescription());
        Picasso.get().load("http://silo.yafetrakan.com/images/"+data.getPhoto()).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(myViewHolder.photo);

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
