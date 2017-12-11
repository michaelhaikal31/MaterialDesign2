package com.example.haikal.materialdesign2.Json_Volly.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haikal.materialdesign2.Json_Volly.Activity.Json_VollyActivity;
import com.example.haikal.materialdesign2.Json_Volly.Model.item_phone;
import com.example.haikal.materialdesign2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by haikal on 10/12/2017.
 */

public class Adapter_Json_Volly extends RecyclerView.Adapter<Adapter_Json_Volly.ViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> list;
    public Adapter_Json_Volly(Context context, ArrayList<HashMap<String,String>> mlist){
        this.context =context;
        this.list = mlist;
    }
    @Override
    public Adapter_Json_Volly.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_phone,parent,false);
        return new ViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(Adapter_Json_Volly.ViewHolder holder, int position) {

        holder.id.setText(list.get(position).get("merk"));

        Glide.with(context)
                .load(list.get(position).get("gambar"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            id =(TextView)itemView.findViewById(R.id.txthape);
            imageView = (ImageView)itemView.findViewById(R.id.imghp);
        }
    }
}
