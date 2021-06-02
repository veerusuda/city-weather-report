package com.weatherInfo.city.weather.report.config;

import com.weatherInfo.city.weather.report.CORSFilter;
import com.weatherInfo.city.weather.report.resource.WeatherReportResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(WeatherReportResource.class);
        register(new CORSFilter());
    }

    @PostConstruct
    public void init() {
        this.SwaggerConfig();
    }

    private void SwaggerConfig() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig swaggerConfigBean = new BeanConfig();
        swaggerConfigBean.setConfigId("City Weather Report");
        swaggerConfigBean.setTitle("City Weather Report");
        swaggerConfigBean.setVersion("v1");
        swaggerConfigBean.setContact("admin");
        swaggerConfigBean.setSchemes(new String[] { "http", "https" });
        swaggerConfigBean.setBasePath("/weather");
        swaggerConfigBean.setResourcePackage("com.weatherInfo.city.weather.report.resource");
        swaggerConfigBean.setPrettyPrint(true);
        swaggerConfigBean.setScan(true);
    }

}
