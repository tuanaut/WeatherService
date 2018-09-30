package com.weather.weather;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Bean
	public Request newRequest() {
		return Mockito.mock(Request.class);
	}
	
	@Bean WeatherService weatherService() {
		return Mockito.mock(WeatherService.class);
	}

}
