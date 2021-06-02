package com.weatherInfo.city.weather.report.resource;

import com.weatherInfo.city.weather.report.response.*;
import com.weatherInfo.city.weather.report.service.WeatherReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
@Api(value = "Weather Report resource", produces = "application/json")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class WeatherReportResource {

    @Autowired
    WeatherReportService service;

    @GET
    @Path("/location/{city}")
    @ApiOperation(value = "Gets Location Details for city", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "location resource found"),
            @ApiResponse(code = 404, message = "location resource not found")
    })
    public Response getLocationDetailsForCity(@PathParam("city") String city) {
        log.info(String.format("Location Request Received for City: %s", city));

        LocationDetails location = service.getLocationDetailsForCity(city);
        if (location != null)
            return Response.status(Response.Status.OK).entity(location).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/current/{city}")
    @ApiOperation(value = "Gets Current Details for city", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "current resource found"),
            @ApiResponse(code = 404, message = "current resource not found")
    })
    public Response getCurrentDetailsForCity(@PathParam("city") String city) {
        log.info(String.format("Current Request Received for City: %s", city));

        CurrentDetails current = service.getCurrentDetailsForCity(city);
        if (current != null)
            return Response.status(Response.Status.OK).entity(current).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/astronomy/{city}")
    @ApiOperation(value = "Gets Astronomy Details for city", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "astronomy resource found"),
            @ApiResponse(code = 404, message = "astronomy resource not found")
    })
    public Response getAstronomyDetailsForCity(@PathParam("city") String city) {
        log.info(String.format("Astronomy Request Received for City: %s", city));

        AstronomyDetails astronomy = service.getAstronomyDetailsForCity(city);
        if (astronomy != null)
            return Response.status(Response.Status.OK).entity(astronomy).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("/weather-report/{city}")
    @ApiOperation(value = "Gets Weather Report for city", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "weather resource found"),
            @ApiResponse(code = 404, message = "weather resource not found")
    })
    public Response getWeatherDetailsForCity(@PathParam("city") String city) {
        log.info(String.format("Weather Report Request Received for City: %s", city));

        Weather weather = service.getWeatherDetailsForCity(city);
        if (weather != null)
            return Response.status(Response.Status.OK).entity(weather).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}

