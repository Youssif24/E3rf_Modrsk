package com.saad.youssif.e3rfmodrsk.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saad.youssif.e3rfmodrsk.Model.Comment;
import com.saad.youssif.e3rfmodrsk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {
    private final List<Comment> commentList;
    Context ctx;

    public CommentsAdapter(List<Comment> cartList, Context context){
        this.commentList = cartList;
        this.ctx=context;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comments_items_layout,viewGroup,false);
        CommentViewHolder viewHolder = new CommentViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        Comment comment=commentList.get(i);
        Picasso.with(ctx).load(comment.getPhotoPath()).into(commentViewHolder.cmt_profileImg);
        commentViewHolder.cmt_std_name.setText(comment.getStdName());
        commentViewHolder.cmt_details.setText(comment.getCmtDetails());
        commentViewHolder.cmt_date.setText(comment.getCmtDate());

    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView cmt_std_name,cmt_details,cmt_date;
        CircleImageView cmt_profileImg;

        public CommentViewHolder(View item) {
            super(item);
            cmt_std_name=item.findViewById(R.id.cmt_std_name);
            cmt_details=item.findViewById(R.id.cmt_detailsTv);
            cmt_date=item.findViewById(R.id.cmt_dateTv);
            cmt_profileImg=item.findViewById(R.id.cmt_profileImg);
        }


    }
}