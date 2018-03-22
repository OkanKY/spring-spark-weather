package com.okanky.weather.stream.service;

import com.okanky.weather.stream.model.WeatherModel;

import java.util.List;

public interface WeatherStreamService {
    List<WeatherModel> getWeatherStream();
}
