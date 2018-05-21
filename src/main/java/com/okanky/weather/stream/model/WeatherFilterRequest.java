package com.okanky.weather.stream.model;

public class WeatherFilterRequest {

    public WeatherDateRequest startDate;
    public WeatherDateRequest endDate;

    public WeatherDateRequest getStartDate() {
        return startDate;
    }

    public void setStartDate(WeatherDateRequest startDate) {
        this.startDate = startDate;
    }

    public WeatherDateRequest getEndDate() {
        return endDate;
    }

    public void setEndDate(WeatherDateRequest endDate) {
        this.endDate = endDate;
    }
}
