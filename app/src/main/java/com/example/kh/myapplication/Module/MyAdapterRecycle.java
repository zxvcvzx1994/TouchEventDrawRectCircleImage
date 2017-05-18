package com.example.kh.myapplication.Module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kh.myapplication.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by kh on 5/16/2017.
 */

public class MyAdapterRecycle extends RecyclerView.Adapter<MyAdapterRecycle.MyHoldViewer> {
    List<Information> data = Collections.emptyList();
    Context context;
    public MyAdapterRecycle (Context context, List<Information> data){
        this.data = data;
        this.context  =context;
    }
    @Override
    public MyHoldViewer onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        MyHoldViewer holder = new MyHoldViewer(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(MyHoldViewer holder, int position) {

        holder.txtTitle.setText(data.get(position).tittle);
        holder.imIcon.setImageResource(data.get(position).icon);


    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHoldViewer extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtTitle;
        private ImageView imIcon;
        public MyHoldViewer(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imIcon = (ImageView) itemView.findViewById(R.id.imIcon);
            txtTitle.setOnClickListener(this);
            imIcon.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(context,String.valueOf( getAdapterPosition()), Toast.LENGTH_SHORT).show();
            delete(getAdapterPosition());
        }
    }
}
