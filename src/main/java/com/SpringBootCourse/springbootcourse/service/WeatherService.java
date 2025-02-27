package com.SpringBootCourse.springbootcourse.service;


import com.SpringBootCourse.springbootcourse.cache.AppCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private   String apiKey;


    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;


    public void getWeather(String city) {
        String api = appCache.appCache.get("weather_api").replace("<apiKey>",apiKey).replace("<location>",city);
        ResponseEntity<String> response = restTemplate.getForEntity(api, String.class);
        logger.info(response.getBody());


    }

}
