package com.okanky.weather.stream.controller;

import com.okanky.weather.stream.facade.WeatherDataFacade;
import com.okanky.weather.stream.model.WeatherFilterRequest;
import com.okanky.weather.stream.model.WeatherFrequenceResponse;
import com.okanky.weather.stream.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RequestMapping("api")
@Controller
public class ApiController {
    @Autowired
    WeatherDataFacade weatherDataFacade;
    @CrossOrigin
    @RequestMapping("wordcount")
    @ResponseBody
    public List<WeatherModel> words() {
        return weatherDataFacade.getWeatherStream().stream().limit(10).collect(toList());
    }

    @CrossOrigin
    @PostMapping("filter")
    @ResponseBody
    public List<WeatherModel> filter(@RequestBody WeatherFilterRequest weatherFilterRequest) {
        return weatherDataFacade.getWeatherStreamFilter(weatherFilterRequest);
    }
    @CrossOrigin
    @PostMapping("frequence")
    @ResponseBody
    public Set<WeatherFrequenceResponse> frequence(@RequestBody WeatherFilterRequest weatherFilterRequest) {
        return weatherDataFacade.getWeatherStreamFilterFrequence(weatherFilterRequest);
    }
}
