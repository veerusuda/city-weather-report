package com.weatherInfo.city.weather.report.service.impl;

import com.weatherInfo.city.weather.report.response.*;
import com.weatherInfo.city.weather.report.service.WeatherReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.HashMap;


@Service
@Slf4j
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Weather weather;

    @Value("${weatherapi.x-rapidapi-key}")
    private String x_rapidapi_key;

    @Value("${weatherapi.x-rapidapi-host}")
    private String x_rapidapi_host;

    @Value("${weatherapi.location.url}")
    private String location_url;

    @Value("${weatherapi.current.url}")
    private String current_url;

    @Value("${weatherapi.astronomy.url}")
    private String astronomy_url;

    public LocationDetails getLocationDetailsForCity(String city) {
        ResponseEntity<LocationDetails> location = null;
        HttpEntity entity = getHeadersDetails();
        Map<String, String> params = new HashMap<>();
        params.put("city", city);

        try {
            location = restTemplate.exchange(
                    location_url,
                    HttpMethod.GET,
                    entity,
                    LocationDetails.class,
                    params);
        } catch (Exception e) {
            log.error(String.format("Error While Fetching the Location Details from Remote Server : %s", ExceptionUtils.getStackTrace(e)));
        }

        return location.getBody();
    }

    public CurrentDetails getCurrentDetailsForCity(String city) {
        ResponseEntity<CurrentDetails> current = null;
        HttpEntity entity = getHeadersDetails();
        Map<String, String> params = new HashMap<>();
        params.put("city", city);

        try {
            current = restTemplate.exchange(
                    current_url,
                    HttpMethod.GET,
                    entity,
                    CurrentDetails.class,
                    params);
        } catch (Exception e) {
            log.error(String.format("Error While Fetching the Current Details from Remote Server : %s", ExceptionUtils.getStackTrace(e)));
        }

        return current.getBody();
    }

    public AstronomyDetails getAstronomyDetailsForCity(String city) {
        ResponseEntity<AstronomyDetails> astronomy = null;
        HttpEntity entity = getHeadersDetails();
        Map<String, String> params = new HashMap<>();
        params.put("city", city);

        try {
            astronomy = restTemplate.exchange(
                    astronomy_url,
                    HttpMethod.GET,
                    entity,
                    AstronomyDetails.class,
                    params);
        } catch (Exception e) {
            log.error(String.format("Error While Fetching the Astronomy Details from Remote Server : %s", ExceptionUtils.getStackTrace(e)));
        }

        return astronomy.getBody();
    }

    public Weather getWeatherDetailsForCity(String city) {

        try {
            weather.setLocation(getLocationDetailsForCity(city).getLocation());
            weather.setCurrent(getCurrentDetailsForCity(city).getCurrent());
            weather.setAstronomy(getAstronomyDetailsForCity(city).getAstronomy());
        } catch (Exception e) {
            log.error(String.format("Error While Fetching the Weather Report Details from Remote Server : %s", ExceptionUtils.getStackTrace(e)));
        }

        return weather;
    }

    public HttpEntity getHeadersDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", x_rapidapi_key);
        headers.set("x-rapidapi-host", x_rapidapi_host);
        headers.setAccessControlAllowOrigin("*");

        HttpEntity entity = new HttpEntity(headers);

        return entity;
    }

}

