package com.weather.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
	
	@Cacheable(CacheConfig.CACHE_ONE)
	public String getWeatherJSON(String zip) throws IOException, JSONException, InterruptedException {
		
		Thread.sleep(5000);
		System.out.println("No cache yet");
		
		int zipcode = Integer.parseInt(zip);
		String apiKey = "6edeb8342b5b5b0ecf6d386f2b3c5b7c";
		String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&appid=" + apiKey;
		System.out.println(zipcode);
		URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

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
	
	@CacheEvict(value = "wind", allEntries = true)
	public String clearCache() {
		System.out.println("Cache cleared");
		return "Cache Cleared";
	}
}
