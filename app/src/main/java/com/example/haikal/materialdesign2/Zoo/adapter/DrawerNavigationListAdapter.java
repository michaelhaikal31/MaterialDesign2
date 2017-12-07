package com.example.haikal.materialdesign2.Zoo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SimpleToDo.SimpleToDoActivity;

/**
 * Created by haikal on 06/12/2017.
 */

public class DrawerNavigationListAdapter extends ArrayAdapter<String> {
    public DrawerNavigationListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.navigation_drawer_list_item, parent );
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.title = (TextView)convertView.findViewById(R.id.title_id);
        viewHolder.title.setText(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView title;
    }
}
