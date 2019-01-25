package com.example.android.basicweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.OnWeatherViewHolderSelectListener {

    private RecyclerView weatherListRV;
    private WeatherAdapter weatherAdapter;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherAdapter = new WeatherAdapter(this);

        weatherListRV = findViewById(R.id.rv_weather_list);

        weatherListRV.setLayoutManager(new LinearLayoutManager(this));
        weatherListRV.setHasFixedSize(true);
        weatherListRV.setAdapter(weatherAdapter);
        weatherListRV.setItemAnimator(new DefaultItemAnimator());

        toast = null;

        for(String var : getResources().getStringArray(R.array.weather_overview)){
            weatherAdapter.addWeather(var);
        }

    }

    @Override
    public void OnWeatherViewHolderSelect(int position) {
        if(toast != null){
            toast.cancel();
        }
        String toastText = getResources().getStringArray(R.array.weather_description)[position];
        toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        toast.show();
    }
}
