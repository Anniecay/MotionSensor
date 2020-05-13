package com.example.motionsensor;

import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewerAdapter extends RecyclerView.Adapter<RecyclerViewerAdapter.ViewHolder> {

    private List<Sensor> sensorList = new ArrayList<Sensor>();

    RecyclerViewerAdapter(List<Sensor> sensorList){
        this.sensorList = sensorList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sensorData.setText(sensorList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sensorData;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorData = itemView.findViewById(R.id.sensorListItem);
        }
    }
}
