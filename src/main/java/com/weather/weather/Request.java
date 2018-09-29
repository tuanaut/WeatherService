package com.weather.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
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
		weatherservice.clearCache_After_15Min();
		return weatherservice.getWeatherJSON(zip);
		
		
	}
	
	@RequestMapping(value = "/evict")
	public void callClearCache(){
		System.out.println("Cache has been evicted");
		weatherservice.clearCache();
	}
	
	
}
