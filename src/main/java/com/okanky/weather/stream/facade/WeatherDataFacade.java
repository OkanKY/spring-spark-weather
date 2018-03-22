package com.okanky.weather.stream.facade;

import com.okanky.weather.stream.model.WeatherModel;

import java.util.List;

public interface WeatherDataFacade {
    List<WeatherModel> getWeatherStream();
}
