package com.okanky.weather.stream.service.impl;

import com.okanky.weather.stream.model.WeatherFrequenceResponse;
import com.okanky.weather.stream.model.WeatherModel;
import com.okanky.weather.stream.service.WeatherFilterService;
import org.apache.commons.collections.CollectionUtils;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherFilterServiceImpl implements WeatherFilterService {
    @Override
    public List<WeatherModel> getFilterData(List<WeatherModel> weatherModels, Date startDate, Date endDate) {
        if (CollectionUtils.isNotEmpty(weatherModels)) {
            return weatherModels.stream().filter(value -> value.getDate().after(startDate) && value.getDate().before(endDate)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Set<WeatherFrequenceResponse> getFilterAndFrequenceData(List<WeatherModel> weatherModels, Date startDate, Date endDate) {
        if (CollectionUtils.isNotEmpty(weatherModels)) {
            weatherModels = weatherModels.stream()
                    .filter(value -> value.getDate().after(startDate) && value.getDate().before(endDate))
                    .sorted(Comparator.comparing(WeatherModel::getDate, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
            return getFrequence(weatherModels);
        }
        return Collections.emptySet();
    }

    private Set<WeatherFrequenceResponse> getFrequence(List<WeatherModel> weatherModels) {
        if (CollectionUtils.isNotEmpty(weatherModels)) {
            Set<WeatherFrequenceResponse> weatherFrequenceResponses = new HashSet<>();
            for (double i = 0.0; i <= 15.0; i += 0.1) {
                for (double a = 0.0; a <= 15.0; a += 0.1) {
                    i=DoubleRounder.round(i, 2);
                    a=DoubleRounder.round(a, 2);
                    weatherFrequenceResponses.add(new WeatherFrequenceResponse(i, a, 0));
                }
            }
            for (int i = 1; i < weatherModels.size(); i++) {
                WeatherFrequenceResponse response=find(weatherFrequenceResponses,weatherModels.get(i - 1),weatherModels.get(i));
                if (response!=null) {
                    response.setFrequence(response.getFrequence() + 1);
                    weatherFrequenceResponses.add(response);
                }
            }

            return weatherFrequenceResponses;
        }
        return Collections.emptySet();
    }
    private WeatherFrequenceResponse find(Set<WeatherFrequenceResponse> weatherFrequenceResponses ,WeatherModel one,WeatherModel two){
        for (WeatherFrequenceResponse response:weatherFrequenceResponses) {
            if(response.getSpeedOne().doubleValue()==one.getSpeed().doubleValue()&&response.getSpeedTwo().doubleValue()==two.getSpeed().doubleValue()){
                return response;
            }
        }
        return null;
    }
}
