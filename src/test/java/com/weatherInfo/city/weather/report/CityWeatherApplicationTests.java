package com.weatherInfo.city.weather.report;

import com.weatherInfo.city.weather.report.response.AstronomyDetails;
import com.weatherInfo.city.weather.report.response.CurrentDetails;
import com.weatherInfo.city.weather.report.response.LocationDetails;
import com.weatherInfo.city.weather.report.service.impl.WeatherReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CityWeatherApplicationTests {

	private final static String city = "Dublin";

	@Autowired
	private WeatherReportServiceImpl service;

	@Test
	public void getLocation() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate date = LocalDate.now();
		String strDate = sdf.format(new Date());
		System.out.println(strDate);
		LocationDetails location= service.getLocationDetailsForCity(city);
		assertEquals(city, location.getLocation().getRegion());
		assertEquals("Ireland", location.getLocation().getCountry());
		assertTrue(location.getLocation().getLocaltime().contains(strDate));
		assertNotNull(location.getLocation().getLat());
		assertNotNull(location.getLocation().getLon());
	}

	@Test
	public void getCurrent() {
		CurrentDetails current = service.getCurrentDetailsForCity(city);
		assertThat(current.getCurrent().getTemp_c()).isBetween(-100f, 65f);
		assertNotNull(current.getCurrent().getCondition().getText());
		assertNotNull(current.getCurrent().getCondition().getIcon());
		assertNotNull(current.getCurrent().getCondition().getCode());
	}

	@Test
	public void getAstronomy() {
		AstronomyDetails astronomy = service.getAstronomyDetailsForCity(city);
		assertNotNull(astronomy.getAstronomy().getAstro().getMoon_phase());
		assertNotNull(astronomy.getAstronomy().getAstro().getSunrise());
		assertNotNull(astronomy.getAstronomy().getAstro().getSunset());
	}

}
