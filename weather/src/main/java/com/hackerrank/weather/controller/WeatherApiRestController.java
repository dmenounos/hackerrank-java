package com.hackerrank.weather.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherHiLo;
import com.hackerrank.weather.service.WeatherService;

@RestController
public class WeatherApiRestController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherApiRestController.class);

	@Autowired
	private WeatherService weatherService;

	@PostMapping(path = "/weather")
	public ResponseEntity<?> create(@RequestBody Weather weather) {
		logger.debug("create: weather={} ", weather);

		weather = weatherService.create(weather);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/erase", params= { "start", "end", "lat", "lon" })
	public ResponseEntity<?> delete(
		@RequestParam("start") String startParam, @RequestParam("end") String endParam,
		@RequestParam("lat") String latParam, @RequestParam("lon") String lonParam) {
		logger.debug("delete: ");

		Date start = parseDate(startParam);
		Date end   = parseDate(endParam);
		Float lat  = parseFloat(latParam);
		Float lon  = parseFloat(lonParam);

		weatherService.delete(start, end, lat, lon);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/weather", params = { "lat", "lon" })
	public ResponseEntity<?> findByLocation(@RequestParam("lat") String latParam, @RequestParam("lon") String lonParam) {
		logger.debug("findByLocation: latParam={}, lonParam={} ", latParam, lonParam);

		Float lat = parseFloat(latParam);
		Float lon = parseFloat(lonParam);

		List<Weather> records = weatherService.findByLocation(lat, lon);

		if (!records.isEmpty()) {
			return new ResponseEntity<>(records, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/weather/temperature", params = { "start", "end" })
	public ResponseEntity<?> findByDate(@RequestParam("start") String startParam, @RequestParam("end") String endParam) {
		logger.debug("findByDate: startParam={}, endParam={} ", startParam, endParam);

		Date start = parseDate(startParam);
		Date end   = parseDate(endParam);

		List<WeatherHiLo> records = weatherService.findByDate(start, end);

		if (!records.isEmpty()) {
			return new ResponseEntity<>(records, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/weather")
	public ResponseEntity<?> findAll() {
		logger.debug("findAll: ");

		List<Weather> records = weatherService.findAll();

		ResponseEntity<List<Weather>> responseEntity = new ResponseEntity<>(records, HttpStatus.OK);
		return responseEntity;
	}

	private Float parseFloat(String param) {
		return Float.valueOf(param);
	}

	private Date parseDate(String param) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.parse(param);
		}
		catch (ParseException e) {
			throw new RuntimeException("bad date input", e);
		}
	}
}
