package com.okanky.weather.stream.facade;

import com.okanky.weather.stream.model.WeatherFilterRequest;
import com.okanky.weather.stream.model.WeatherFrequenceResponse;
import com.okanky.weather.stream.model.WeatherModel;

import java.util.List;
import java.util.Set;

public interface WeatherDataFacade {
    List<WeatherModel> getWeatherStream();
    List<WeatherModel> getWeatherStreamFilter(WeatherFilterRequest weatherFilterRequest);
    Set<WeatherFrequenceResponse> getWeatherStreamFilterFrequence(WeatherFilterRequest weatherFilterRequest);
}
