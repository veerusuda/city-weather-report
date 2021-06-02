package com.weatherInfo.city.weather.report.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Current {

    @JsonProperty("temp_c")
    private float temp_c;

    @JsonProperty("condition")
    private Condition condition;

}

