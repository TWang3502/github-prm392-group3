package com.example.prj2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OSoAdapter extends ArrayAdapter<Integer> {
    private Context ct;
    private ArrayList<Integer> arr;
    public OSoAdapter(@NonNull Context context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.ct=context;
        this.arr=new ArrayList<>(objects);
    }

    @Override
    public void notifyDataSetChanged() {
        arr=Datagame.getDatagame().getArrSo();
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.itetmovuong,null);
        }
        if(arr.size()>0){
            ovuong o=(ovuong) convertView.findViewById(R.id.txvOVuong);
            o.setTett(arr.get(position));
        }
        return convertView;

    }
}
