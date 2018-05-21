package com.okanky.weather.stream.facade.impl;

import com.okanky.weather.stream.facade.WeatherDataFacade;
import com.okanky.weather.stream.model.WeatherDateRequest;
import com.okanky.weather.stream.model.WeatherFilterRequest;
import com.okanky.weather.stream.model.WeatherFrequenceResponse;
import com.okanky.weather.stream.model.WeatherModel;
import com.okanky.weather.stream.service.WeatherFilterService;
import com.okanky.weather.stream.service.WeatherModelService;
import com.okanky.weather.stream.service.WeatherStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class WeatherDataFacadeImpl implements WeatherDataFacade {
    @Autowired
    private WeatherStreamService weatherStreamService;
    @Autowired
    private WeatherModelService weatherModelService;
    @Autowired
    private WeatherFilterService weatherFilterService;
    @Override
    public List<WeatherModel> getWeatherStream() {
        List<WeatherModel> weatherModels = weatherStreamService.getWeatherStream();
        weatherModelService.deleteAll();
        weatherModelService.save(weatherModels);
        return weatherModelService.getAll();
    }

    @Override
    public List<WeatherModel> getWeatherStreamFilter(WeatherFilterRequest weatherFilterRequest) {
        return weatherFilterService.getFilterData(getWeatherStream(),convertDate(weatherFilterRequest.getStartDate()),convertDate(weatherFilterRequest.endDate));
    }

    @Override
    public Set<WeatherFrequenceResponse> getWeatherStreamFilterFrequence(WeatherFilterRequest weatherFilterRequest) {
        return weatherFilterService.getFilterAndFrequenceData(getWeatherStream(),convertDate(weatherFilterRequest.getStartDate()),convertDate(weatherFilterRequest.endDate));
    }

    private Date convertDate(WeatherDateRequest weatherDateRequest){
        Date date=new Date();
        date.setYear(weatherDateRequest.getYear());
        date.setMonth(weatherDateRequest.getMonth());
        date.setDate(weatherDateRequest.getDay());
        date.setHours(weatherDateRequest.getHour());
        return date;
    }
}
