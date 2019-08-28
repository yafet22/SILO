package silo.com.silo.UI.UI;

import android.content.Context;
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

import java.util.List;

import silo.com.silo.R;
import silo.com.silo.UI.Controller.CommentList;
import silo.com.silo.UI.Controller.Comment;
import silo.com.silo.UI.Controller.Post;
import silo.com.silo.UI.Controller.PostList;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.MyViewHolder> {
    private Context mContext;
    private List<Comment> CommentBundle;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView user, content;
        public LinearLayout wrapper;

        public  MyViewHolder(View itemView){
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.username);
            content = (TextView) itemView.findViewById(R.id.content_comment);

            wrapper = itemView.findViewById(R.id.rc_comment);

        }
    }

    public AdapterComment(List<Comment> CommentBundle, Context mContext) {
        this.mContext = mContext;
        this.CommentBundle = CommentBundle;
    }

    @NonNull
    @Override
    public AdapterComment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recycle_comment, viewGroup, false);

        return new AdapterComment.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComment.MyViewHolder myViewHolder, final int i) {

        final Comment data = CommentBundle.get(i);
        final int ifinal = myViewHolder.getAdapterPosition();


        myViewHolder.user.setText(data.getUser());
        myViewHolder.content.setText(data.getContent());


    }

    @Override
    public int getItemCount() {
        return CommentBundle.size();
    }
}
