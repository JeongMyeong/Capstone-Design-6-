package e.evolc.chatapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private RecyclerView mRecyclerView;

    private List<Chat> mChat;
    private int stMyuser;
    private ArrayList<String> url;
    private int urlposition = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imgview;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imgview = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Chat> mChat, int stMyuser, ArrayList<String> url, RecyclerView mRecyclerView)
    {
        this.mChat = mChat;
        this.stMyuser = stMyuser;
        this.url = url;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public int getItemViewType(int position) {
        if(mChat.get(position).getUser() == 1){
            return 1;
        }else {
            return 2;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v;

        if(viewType == 1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.right_text_view, parent, false);
        }else{
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
        }

        // create a new view
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mChat.get(position).getText());

        Log.d("TEST", "유저 : " + mChat.get(position).getUser());

        if(url.get(position).isEmpty() && mChat.get(position).getUser() == 2){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,0);
            holder.imgview.setLayoutParams(params);
            holder.imgview.setPadding(0,0,0,0);

            Log.d("TEST", "지나감");

        }

        if(url.size() != 0 && !url.get(position).isEmpty()) {
            //Log.d("TEST", "이미지 :" + url.size() + " " + urlposition);
            Log.d("TEST", "경로 :" + url.get(position));

            Log.d("TEST", "채팅 :" + mChat.size() + " " + position);
            Glide.with(holder.itemView.getContext())
                    .load(url.get(position))
                    .override(650, 650)
                    .into(holder.imgview);
            mRecyclerView.smoothScrollToPosition(mChat.size()-1);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mChat.size();
    }

}
