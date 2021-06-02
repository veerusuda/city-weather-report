package com.weatherInfo.city.weather.report.service;

import com.weatherInfo.city.weather.report.response.*;

public interface WeatherReportService {

    LocationDetails getLocationDetailsForCity(String city);

    CurrentDetails getCurrentDetailsForCity(String city);

    AstronomyDetails getAstronomyDetailsForCity(String city);

    Weather getWeatherDetailsForCity(String city);

}


