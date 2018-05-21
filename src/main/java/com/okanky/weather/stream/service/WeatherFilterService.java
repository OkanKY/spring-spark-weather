package com.okanky.weather.stream.service;

import com.okanky.weather.stream.model.WeatherFrequenceResponse;
import com.okanky.weather.stream.model.WeatherModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface WeatherFilterService {
    List<WeatherModel> getFilterData(List<WeatherModel> weatherModels,Date startDate,Date endDate);
    Set<WeatherFrequenceResponse> getFilterAndFrequenceData(List<WeatherModel> weatherModels, Date startDate, Date endDate);
}
