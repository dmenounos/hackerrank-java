package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherHiLo {

	@JsonProperty("city")
	private String cityName;

	@JsonProperty("state")
	private String stateName;

	@JsonProperty("lat")
	private Float latitude;

	@JsonProperty("lon")
	private Float longitude;

	private Float highest;

	private Float lowest;

	public WeatherHiLo(String cityName, String stateName, Float latitude, Float longitude, Float highest, Float lowest) {
		this.cityName = cityName;
		this.stateName = stateName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.highest = highest;
		this.lowest = lowest;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getHighest() {
		return highest;
	}

	public void setHighest(Float highest) {
		this.highest = highest;
	}

	public Float getLowest() {
		return lowest;
	}

	public void setLowest(Float lowest) {
		this.lowest = lowest;
	}

	@Override
	public String toString() {
		return "WeatherHiLo { "
			+ "cityName=" + cityName + ", "
			+ "stateName=" + stateName + ", "
			+ "latitude=" + latitude + ", "
			+ "longitude=" + longitude + ", "
			+ "highest=" + highest + ", "
			+ "lowest=" + lowest + " }";
	}
}
