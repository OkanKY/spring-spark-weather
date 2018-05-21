package com.okanky.weather.stream.model;

public class WeatherFrequenceResponse {
    private Double speedOne;
    private Double speedTwo;
    private Integer frequence;

    public WeatherFrequenceResponse(Double speedOne, Double speedTwo, Integer frequence) {
        this.speedOne = speedOne;
        this.speedTwo = speedTwo;
        this.frequence = frequence;
    }

    public Double getSpeedOne() {
        return speedOne;
    }

    public void setSpeedOne(Double speedOne) {
        this.speedOne = speedOne;
    }

    public Double getSpeedTwo() {
        return speedTwo;
    }

    public void setSpeedTwo(Double speedTwo) {
        this.speedTwo = speedTwo;
    }

    public Integer getFrequence() {
        return frequence;
    }

    public void setFrequence(Integer frequence) {
        this.frequence = frequence;
    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        WeatherFrequenceResponse other = (WeatherFrequenceResponse) obj;
//        if (i == null) {
//            if (other. != null)
//                return false;
//        } else if (!i.equals(other.i))
//            return false;
//        return true;
//    }
}
