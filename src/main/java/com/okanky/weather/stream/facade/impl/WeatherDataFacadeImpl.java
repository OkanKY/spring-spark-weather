package com.okanky.weather.stream.facade.impl;

import com.okanky.weather.stream.facade.WeatherDataFacade;
import com.okanky.weather.stream.model.WeatherModel;
import com.okanky.weather.stream.service.WeatherModelService;
import com.okanky.weather.stream.service.WeatherStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataFacadeImpl implements WeatherDataFacade {
    @Autowired
    private WeatherStreamService weatherStreamService;
    @Autowired
    private WeatherModelService weatherModelService;
    @Override
    public List<WeatherModel> getWeatherStream() {
        List<WeatherModel> weatherModels = weatherStreamService.getWeatherStream();
        weatherModelService.deleteAll();
        weatherModelService.save(weatherModels);
        return weatherModelService.getAll();
    }
}
