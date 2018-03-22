package com.okanky.weather.stream.service.impl;


import com.okanky.weather.stream.excell.ExcellService;
import com.okanky.weather.stream.model.WeatherData;
import com.okanky.weather.stream.model.WeatherModel;
import com.okanky.weather.stream.service.WeatherStreamService;
import com.okanky.weather.stream.util.ConfigUtil;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WeatherStreamServiceImpl implements WeatherStreamService{
    @Autowired
    private SparkSession sparkSession;

    @Autowired
    private ExcellService excellService;

    /**
     *
     * @return
     */
    @Autowired
    public List<WeatherModel> getWeatherStream() {
        List<WeatherData> words = excellService.getDataFromExcell();

        return words.stream().map(new Function<WeatherData, WeatherModel>() {
            @Override
            public WeatherModel apply(WeatherData weatherData) {
                String[] speed_direction = getSplitValueSpeed_Direction(weatherData.getSpeed_direction());
                return new WeatherModel(getDate(weatherData.getYear(), weatherData.getMonth(), weatherData.getDay(), weatherData.getHour()), getDoubleValue(ConfigUtil.resolve(()->speed_direction[0]).orElse("0.0")), ConfigUtil.resolve(()->speed_direction[1]).orElse("/SSE"));
            }
        }).collect(Collectors.toList());
    }

    /**
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    private Date getDate(String year, String month, String day, String hour) {
        Date date = new Date();
        try {
        date.setYear(getIntegerValue(year));
        date.setMonth(getIntegerValue(month));
        date.setDate(getIntegerValue(day));
        date.setHours(getIntegerValue(hour));
        }catch (Exception e){

        }
        return date;
    }

    /**
     *
     * @param speed_direction
     * @return
     */
    private String[] getSplitValueSpeed_Direction(String speed_direction) {
        return speed_direction.split("  ");
    }

    /**
     *
     * @param value
     * @return
     */
    private Integer getIntegerValue(String value) {
        return ConfigUtil.resolve(()->Integer.parseInt(value)).orElse(0);
    }

    /**
     *
     * @param value
     * @return
     */
    private Double getDoubleValue(String value) {
        return ConfigUtil.resolve(()->Double.parseDouble(value)).orElse(0.0);
    }
}