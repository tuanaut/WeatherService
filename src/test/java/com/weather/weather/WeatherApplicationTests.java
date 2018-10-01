package com.weather.weather;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class)
@WebMvcTest(WeatherService.class)
public class WeatherApplicationTests {
	
	
	@Autowired
	private MockMvc mvc;
	
	//If correct path is given with a valid zipcode, status code should be 200
	@Test
	public void validURL_status200() throws Exception {
		mvc.perform(get("/api/v1/wind/89147"));
	}
	
	//Test for validateZip method with a valid zip code
	@Test
	public void ValidZipTest() {
		String validZip = "89148";
		boolean thrown = false;
		WeatherService service = new WeatherService();
		
		try {
			service.validateZip(validZip);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			thrown = true;
		}
		
		assertFalse(thrown);
	}
	
	// Test for validateZip method with an invalid zip code
	@Test
	public void InvalidZipTest() {
		String invalidZip = "8914a";
		boolean thrown = false;
		WeatherService service = new WeatherService();

		
		try {
			service.validateZip(invalidZip);
		}
		catch(Exception e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}


}
