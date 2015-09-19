package com.example.macbookpro.designexample.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.macbookpro.designexample.R;
import com.example.macbookpro.designexample.activity.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by macbookpro on 30/08/15.
 */
public class NavigationDrawerAdapter  extends  RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    List<NavDrawerItem> data =  Collections.emptyList();
    private LayoutInflater layoutInflater;
    private Context context;

    public NavigationDrawerAdapter(List<NavDrawerItem> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void  delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.nav_drawer_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current =  data.get(position);
        holder.title.setText(current.getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder {

      TextView  title;

        public MyViewHolder(View view){
            super(view);
             title = (TextView) view.findViewById(R.id.title);
        }

    }

}
