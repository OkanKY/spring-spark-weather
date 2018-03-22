package com.okanky.weather.stream.dao;

import com.okanky.weather.stream.model.WeatherModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherModelDao extends MongoRepository<WeatherModel, String> {
}
