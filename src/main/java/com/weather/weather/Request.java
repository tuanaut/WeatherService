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

import com.weather.exception.InvalidZipCodeException;

//Controller class to handle requests with provided path
@RestController
@Configuration
@EnableCaching
public class Request{
	
	@Autowired
	private WeatherService weatherservice;
	
	//Call service to get the wind resource when path provided = "/api/v1/wind/zipcode
	@RequestMapping(value = "/api/v1/wind/{zipcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public String getWeather(@PathVariable("zipcode") String zip) throws IOException, JSONException, InterruptedException, InvalidZipCodeException {
		return weatherservice.getWeatherJSON(zip);
		
	}
	
	// Clear the cache when path provided = "/evict"
	// This provides a CLI to clear the cache using curl
	@RequestMapping(value = "/evict")
	public void callClearCache(){
		System.out.println("Cache has been evicted");
		weatherservice.clearCache();
	}
	

	
	
}
