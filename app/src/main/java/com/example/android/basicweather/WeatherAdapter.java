package com.example.android.basicweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends  RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>{

    private ArrayList<String> mWeatherList;
    private  OnWeatherViewHolderSelectListener mSelectedWeatherListener;

    public interface OnWeatherViewHolderSelectListener{
        void OnWeatherViewHolderSelect(int position);
    }

    public WeatherAdapter(OnWeatherViewHolderSelectListener selectedWeatherListener){
        mWeatherList = new ArrayList<String>();
        mSelectedWeatherListener = selectedWeatherListener;
    }

    public void addWeather(String weather){
        mWeatherList.add(weather);
        notifyItemInserted(mWeatherList.size()-1);
    }

    @Override
    public int getItemCount(){
        return mWeatherList.size();
    }

    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.weather_list_item, viewGroup, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i){
        String weather = mWeatherList.get(adapterPositionToArrayIndex(i));
        weatherViewHolder.bind(weather);
    }

    public int adapterPositionToArrayIndex(int i){
        return mWeatherList.size() - i - 1;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{
        private TextView mWeatherTV;

        public  WeatherViewHolder(View itemView){
            super(itemView);
            mWeatherTV = itemView.findViewById(R.id.tv_weather_text);
            mWeatherTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedWeatherListener.OnWeatherViewHolderSelect(adapterPositionToArrayIndex(getAdapterPosition()));
                }
            });
        }

        public void bind(String todo){
            mWeatherTV.setText(todo);
        }
    }
}
