package com.okanky.weather.stream.service.impl;

import com.okanky.weather.stream.dao.WeatherModelDao;
import com.okanky.weather.stream.model.WeatherModel;
import com.okanky.weather.stream.service.WeatherModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WeatherModelServiceImpl implements WeatherModelService {
    @Autowired
    private WeatherModelDao weatherModelDao;
    @Override
    public void deleteAll() {
        weatherModelDao.deleteAll();
    }

    @Override
    public void save(List<WeatherModel> weatherModels) {
        weatherModelDao.save(weatherModels);
    }

    @Override
    public List<WeatherModel> getAll() {
        return weatherModelDao.findAll();
    }
}
