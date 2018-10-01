package com.weather.weather;

import java.io.IOException;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@EnableCaching
public class Request{
	
	@Autowired
	private WeatherService weatherservice;
	
	@RequestMapping(value = "/api/v1/wind/{zipcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public String getWeather(@PathVariable("zipcode") String zip) throws IOException, JSONException, InterruptedException {
		return weatherservice.getWeatherJSON(zip);
		
	}
	
	@RequestMapping(value = "/evict")
	public void callClearCache(){
		System.out.println("Cache has been evicted");
		weatherservice.clearCache();
	}
	

	
	
}
