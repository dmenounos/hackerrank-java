package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty("city")
	private String cityName;

	@JsonProperty("state")
	private String stateName;

	@JsonProperty("lat")
	private Float latitude;

	@JsonProperty("lon")
	private Float longitude;

	public Location() {
	}

	public Location(String cityName, String stateName, Float latitude, Float longitude) {
		this.cityName = cityName;
		this.stateName = stateName;
		this.latitude = latitude;
		this.longitude = longitude;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Location other = (Location) obj;

		if (cityName == null && other.cityName != null || 
			cityName != null && !cityName.equals(other.cityName)) {
			return false;
		}

		if (latitude == null && other.latitude != null || 
			latitude != null && !latitude.equals(other.latitude)) {
			return false;
		}

		if (longitude == null && other.longitude != null || 
			longitude != null && !longitude.equals(other.longitude)) {
			return false;
		}

		if (stateName == null && other.stateName != null || 
			stateName != null && !stateName.equals(other.stateName)) {
			return false;
		}

		return true;
	}
}
