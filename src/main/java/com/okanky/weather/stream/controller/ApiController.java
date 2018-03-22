package com.okanky.weather.stream.controller;

import com.okanky.weather.stream.facade.WeatherDataFacade;
import com.okanky.weather.stream.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("api")
@Controller
public class ApiController {
    @Autowired
    WeatherDataFacade weatherDataFacade;

    @RequestMapping("wordcount")
    @ResponseBody
    public List<WeatherModel> words() {
        return weatherDataFacade.getWeatherStream();
    }
}
