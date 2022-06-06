package com.hackerrank.weather.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hackerrank.weather.model.Weather;

@Repository
public class Database {

	/** In memory database. */
	private final Map<Long, Weather> records = new HashMap<>();

	public Map<Long, Weather> getRecords() {
		return records;
	}
}
