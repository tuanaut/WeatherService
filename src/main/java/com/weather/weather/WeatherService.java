package com.weather.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.weather.exception.InvalidZipCodeException;

//Service class to complete logic to retrieve wind resource and return to controller
@Service
public class WeatherService {
	
	/*
	 * Method used to retrieve wind resource from the Open Weather Map api. Once retrieved,
	 * the data is cached for 15 minutes before being cleared.
	 */
	@Cacheable(CacheConfig.CACHE_ONE)
	public String getWeatherJSON(String zip) throws IOException, JSONException, InterruptedException, InvalidZipCodeException {
		
		//Call method to validate if the zip provided as path param is a valid zip code
		validateZip(zip);
		
		int zipcode = Integer.parseInt(zip);
		
		//Key needed to use api
		String apiKey = "6edeb8342b5b5b0ecf6d386f2b3c5b7c";
		
		//GET request
		String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&appid=" + apiKey;
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
	     
	    //Create json object from response
	    JSONObject json_obj = new JSONObject(response.toString());
	   
	    //Get wind resource from json
	    JSONObject wind = json_obj.getJSONObject("wind");
	     
	    System.out.println("Wind Speed : " + wind.getFloat("speed"));
	    System.out.println("Wind Direction: " + wind.getFloat("deg"));
	     
	    //Return wind resource
		return wind.toString();
	}
	
	
	//Method to clear the cache
	@CacheEvict(value = "wind", allEntries = true)
	public String clearCache() {
		System.out.println("Cache cleared");
		return "Cache Cleared";
	}
	
	//Method to validate if the zip code provided is a valid zip code
	public void validateZip(String zip) throws InvalidZipCodeException {
		
		//Java regex
		String regex = "^[0-9]{5}$";
		 
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(zip);
		
		if(!matcher.matches()) {
			throw new InvalidZipCodeException(zip);
		}
		
	}
}
