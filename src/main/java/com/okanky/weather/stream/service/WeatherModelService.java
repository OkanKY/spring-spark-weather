package com.okanky.weather.stream.service;

import com.okanky.weather.stream.model.WeatherModel;


import java.util.List;

public interface WeatherModelService {
    void deleteAll();
    void save(List<WeatherModel> weatherDataList);
    List<WeatherModel> getAll();
}
