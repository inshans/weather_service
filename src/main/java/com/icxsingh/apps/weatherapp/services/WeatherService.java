package com.icxsingh.apps.weatherapp.services;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class WeatherService {

	public String getWeatherService(String cityName) {

		GlobalWeather gw = new GlobalWeather();
		GlobalWeatherSoap gws = gw.getGlobalWeatherSoap();

		String weatherInfo = gws.getWeather(cityName, "");

		return weatherInfo;
	}
}
