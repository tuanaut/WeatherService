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
	
	@RequestMapping(value = "/api/v1/wind/{zipcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@Cacheable("wind")
	public String sayHelloHTML(@PathVariable("zipcode") String zip) throws IOException, JSONException, InterruptedException {
		
		Thread.sleep(5000);
		System.out.println("No cache yet");
		
		int zipcode = Integer.parseInt(zip);
		String apiKey = "6edeb8342b5b5b0ecf6d386f2b3c5b7c";
		String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&appid=" + apiKey;
		System.out.println(zipcode);
		URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     
	     in.close();
	     //print in String
	     
	     JSONObject json_obj = new JSONObject(response.toString());
	   
	     
	     JSONObject wind = json_obj.getJSONObject("wind");
	     
	     System.out.println("Wind Speed : " + wind.getFloat("speed"));
	     System.out.println("Wind Direction: " + wind.getFloat("deg"));
	     
	     //System.out.println(response.toString());
		return wind.toString();
	}
	
	@RequestMapping(value = "/evict")
	@CacheEvict(value = "wind")
	public String callCacheEvict(){
		System.out.println("Cache has been evicted");
		return "Evict";
	}
	
	
}
