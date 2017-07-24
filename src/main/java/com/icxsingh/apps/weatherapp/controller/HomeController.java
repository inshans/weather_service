package com.icxsingh.apps.weatherapp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icxsingh.apps.weatherapp.model.CityWeather;
import com.icxsingh.apps.weatherapp.services.WeatherService;

@Controller
public class HomeController {

	Logger logger = Logger.getLogger("HomeController");

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response, HttpServletRequest request) throws IOException {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "getWeather", method = RequestMethod.GET)
	public ModelAndView getWeather(HttpServletResponse response, HttpServletRequest request)
			throws IOException, InterruptedException {

		String c = request.getParameter("city");
		String[] cities = c.split(",");
		ArrayList<CityWeather> weatherList = new ArrayList<>();

		weatherList = this.callWeatherService(cities);

		ModelAndView model = new ModelAndView("weather_results");
		model.addObject("results", weatherList);

		return model;
	}


	private ArrayList<CityWeather> callWeatherService(String[] cities) throws InterruptedException {

		String weatherData = "";
		CityWeather cityWeather = new CityWeather();
		ArrayList<CityWeather> weatherList = new ArrayList<>();

		WeatherService ws = new WeatherService();
		if (cities != null) {

			logger.debug("--- Total Cities: " + cities.length + " ---");
			System.out.println("--- Total Cities: " + cities.length + " ---");
			if (cities.length > 0) {

				for (String cityName : cities) {
					if (cityName != null || cityName != "") {

						cityWeather = new CityWeather();
						weatherData = ws.getWeatherService(cityName.trim());

						cityWeather.setCityName(cityName.toUpperCase());
						cityWeather.setWeatherData(weatherData);
						weatherList.add(cityWeather);
						Thread.sleep(500);
					}

					logger.info("Weather Data: " + weatherData + " for " + cityName.trim());
					System.out.println("Weather Data: " + weatherData + " for " + cityName.trim());
				}
			}
		}

		return weatherList;

	}

}
