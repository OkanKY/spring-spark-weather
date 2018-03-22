package com.okanky.weather.stream.model;

public class WeatherData {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String speed_direction;

    public WeatherData(String year, String month, String day, String hour, String speed_direction) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.speed_direction = speed_direction;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSpeed_direction() {
        return speed_direction;
    }

    public void setSpeed_direction(String speed_direction) {
        this.speed_direction = speed_direction;
    }
}
