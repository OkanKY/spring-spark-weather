package com.okanky.weather.stream.excell;

import com.okanky.weather.stream.model.WeatherData;

import java.util.List;

public interface ExcelService
{
    List<WeatherData> getDataFromExcell();
}
