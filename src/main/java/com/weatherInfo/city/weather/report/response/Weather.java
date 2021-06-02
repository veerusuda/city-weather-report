package com.weatherInfo.city.weather.report.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Weather {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private Current current;

    @JsonProperty("astronomy")
    private Astronomy astronomy;

}

