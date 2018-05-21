package com.okanky.weather.stream.service.impl;


import com.okanky.weather.stream.excell.ExcelService;
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
    private ExcelService excelService;

    /**
     *
     * @return
     */
    @Autowired
    public List<WeatherModel> getWeatherStream() {
        List<WeatherData> words = excelService.getDataFromExcell();
//        List<WeatherModel> weatherModels=new ArrayList<>();
//        for(int i=0; i<words.size();i++){
//            final int index=i;
//            Function<WeatherData,WeatherModel> function=new Function<WeatherData, WeatherModel>() {
//                @Override
//                public WeatherModel apply(WeatherData weatherData) {
//                    String[] speed_direction = getSplitValueSpeed_Direction(weatherData.getSpeed_direction());
//                    return new WeatherModel(getDate(weatherData.getYear(), weatherData.getMonth(), weatherData.getDay(), weatherData.getHour(),words,index), getDoubleValue(ConfigUtil.resolve(()->speed_direction[0]).orElse("0.0")), ConfigUtil.resolve(()->speed_direction[1]).orElse("/SSE"));
//                }
//            };
//            weatherModels.add(function.apply(words.get(i)));
//        }
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
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    private Date getDate(String year, String month, String day, String hour, List<WeatherData> words, Integer index) {
        Date date = new Date();
        try {
            if(year!=null&&!year.isEmpty()){
                date.setYear(getIntegerValue(year));
            }else{
                date.setYear(findYear(words,index));
            }
            if(month!=null&&!month.isEmpty()){
                date.setMonth(getIntegerValue(month));
            }else{
                date.setMonth(findYear(words,index));
            }
            if(day!=null&&!day.isEmpty()){
                date.setDate(getIntegerValue(day));
            }else{
                date.setDate(findYear(words,index));
            }
            if(hour!=null&&!hour.isEmpty()){
                date.setHours(getIntegerValue(hour));
            }else{
                date.setHours(findYear(words,index));
            }
        }catch (Exception e){

        }
        return date;
    }

    private int findYear(List<WeatherData> words, Integer index) {
        for (int i =index+1;i<words.size();i++){
            if(words.get(i).getYear()!=null&&!words.get(i).getYear().isEmpty()){
                return getIntegerValue(words.get(i).getYear());
            }
        }
        return 1;
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