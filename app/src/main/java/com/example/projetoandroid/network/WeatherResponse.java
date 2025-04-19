package com.example.projetoandroid.network;


import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("current_weather")
    public CurrentWeather current_weather;

    public static class CurrentWeather {
        public double temperature;
        public double windspeed;
    }
}