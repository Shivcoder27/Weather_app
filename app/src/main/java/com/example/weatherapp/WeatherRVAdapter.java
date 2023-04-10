package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<WeatherRVModel> weatherRVModelArrayList;
    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        WeatherRVModel model = weatherRVModelArrayList.get(position);
        holder.temperatureTv.setText(model.getTemperature()+"°C");
        Picasso.get().load("http:".concat(model.getIcon())).into(holder.conditionTv);
        holder.windTv.setText(model.getWindSpeed()+"km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh-mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try{
            Date t = input.parse(model.getTime());
            assert t != null;
            holder.timeTv.setText(output.format(t));
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView windTv,temperatureTv,timeTv;
        private ImageView conditionTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTv = itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTv = itemView.findViewById(R.id.idTVtemperature);
          timeTv   = itemView.findViewById(R.id.idTVtime);
           conditionTv  = itemView.findViewById(R.id.idIVCondition);

        }
    }
}
